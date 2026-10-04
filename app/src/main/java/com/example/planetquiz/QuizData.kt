package com.example.planetquiz

import androidx.annotation.StringRes

/** [correctIndex] is the position of the right planet in the "planets" string-array. */
data class Question(
    @StringRes val text: Int,
    val correctIndex: Int,
    @StringRes val detail: Int
)

object QuizData {
    val questions = listOf(
        Question(R.string.question_1, correctIndex = 4, detail = R.string.detail_1), // Jupiter
        Question(R.string.question_2, correctIndex = 5, detail = R.string.detail_2), // Saturn
        Question(R.string.question_3, correctIndex = 6, detail = R.string.detail_3)  // Uranus
    )
}