package com.example.planetquiz

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class MultipleChoiceFragment : Fragment() {

    companion object {
        private const val ARG_QUESTION = "question_index"

        fun newInstance(questionIndex: Int) = MultipleChoiceFragment().apply {
            arguments = Bundle().apply { putInt(ARG_QUESTION, questionIndex) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_multiple_choice, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val questionIndex = requireArguments().getInt(ARG_QUESTION)

        view.findViewById<TextView>(R.id.tv_question)
            .setText(QuizData.questions[questionIndex].text)

        val container = view.findViewById<LinearLayout>(R.id.options_container)
        val planets = resources.getStringArray(R.array.planets)

        planets.forEachIndexed { planetIndex, planetName ->
            // ContextThemeWrapper applies the QuizButton style to a code-created button
            val button = Button(
                ContextThemeWrapper(requireContext(), R.style.QuizButton),
                null, 0
            ).apply {
                text = planetName
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    resources.getDimensionPixelSize(R.dimen.button_height)
                ).apply { setMargins(0, 16, 0, 16) }
                setOnClickListener {
                    parentFragmentManager.commit {
                        replace(
                            R.id.fragment_container,
                            AnswersFragment.newInstance(questionIndex, planetIndex)
                        )
                        addToBackStack(null)
                    }
                }
            }
            container.addView(button)
        }
    }
}