package com.example.planetquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class QuestionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_questions, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val buttonIds = listOf(R.id.btn_question_1, R.id.btn_question_2, R.id.btn_question_3)

        buttonIds.forEachIndexed { index, id ->
            view.findViewById<Button>(id).setOnClickListener {
                parentFragmentManager.commit {
                    replace(R.id.fragment_container, MultipleChoiceFragment.newInstance(index))
                    addToBackStack(null)
                }
            }
        }
    }
}