// Question.kt
package com.example.quiz

data class Question(val question: String, val answers: List<String>, val correctAnswerIndices: List<Int>, val requiredAnswersCount: Int)
