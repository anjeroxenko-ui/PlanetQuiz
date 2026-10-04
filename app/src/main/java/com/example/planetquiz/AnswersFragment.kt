package com.example.planetquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class AnswersFragment : Fragment() {

    companion object {
        private const val ARG_QUESTION = "question_index"
        private const val ARG_CHOSEN = "chosen_index"

        fun newInstance(questionIndex: Int, chosenIndex: Int) = AnswersFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_QUESTION, questionIndex)
                putInt(ARG_CHOSEN, chosenIndex)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_answers, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val questionIndex = requireArguments().getInt(ARG_QUESTION)
        val chosenIndex = requireArguments().getInt(ARG_CHOSEN)
        val question = QuizData.questions[questionIndex]
        val isCorrect = chosenIndex == question.correctIndex

        view.findViewById<TextView>(R.id.tv_question).setText(question.text)

        val resultView = view.findViewById<TextView>(R.id.tv_result)
        val actionButton = view.findViewById<Button>(R.id.btn_action)

        if (isCorrect) {
            resultView.text = "${getString(R.string.correct)} ${getString(question.detail)}"
            actionButton.setText(R.string.back_to_questions)
            actionButton.setOnClickListener {
                // Pop everything back to the first (questions) screen
                parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        } else {
            val correctName = resources.getStringArray(R.array.planets)[question.correctIndex]
            resultView.text = getString(R.string.wrong, correctName)
            actionButton.setText(R.string.try_again)
            actionButton.setOnClickListener {
                parentFragmentManager.popBackStack() // back to the answer options
            }
        }
    }
}