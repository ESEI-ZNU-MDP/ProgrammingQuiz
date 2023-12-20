// MainActivity.kt
package com.example.quiz

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val model = QuizModel()
    private lateinit var questionTextView: TextView
    private lateinit var answerCheckBoxes: List<CheckBox>
    private lateinit var scoreTextView: TextView
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.questionTextView)
        answerCheckBoxes = listOf(
            findViewById(R.id.answerCheckBox1),
            findViewById(R.id.answerCheckBox2),
            findViewById(R.id.answerCheckBox3),
            findViewById(R.id.answerCheckBox4)
        )
        scoreTextView = findViewById(R.id.scoreTextView)
        submitButton = findViewById(R.id.submitButton)

        // Установим початкове значення scoreTextView
        scoreTextView.text = getString(R.string.score_label, model.score)

        updateQuestion()

        submitButton.setOnClickListener {
            onAnswerSubmitted()
        }

        // Додамо слухача зміни стану чекбоксів
        answerCheckBoxes.forEach { checkBox ->
            checkBox.setOnCheckedChangeListener { _, _ ->
                // Перевіряємо, чи хоча б один чекбокс вибраний, щоб активувати кнопку
                submitButton.isEnabled = answerCheckBoxes.any { it.isChecked }
            }
        }
    }

    private fun updateQuestion() {
        if (model.currentQuestionIndex < model.questions.size) {
            val currentQuestion = model.questions[model.currentQuestionIndex]
            questionTextView.text = currentQuestion.question

            answerCheckBoxes.forEachIndexed { index, checkBox ->
                checkBox.text = currentQuestion.answers[index]
                checkBox.isChecked = false
            }

            // Перевіряємо, чи хоча б один чекбокс вибраний, щоб активувати кнопку
            submitButton.isEnabled = answerCheckBoxes.any { it.isChecked }

            if (currentQuestion.requiredAnswersCount > 1) {
                questionTextView.append(" (several answer options)")
            }
        }
    }

    private fun onAnswerSubmitted() {
        val currentQuestion = model.questions[model.currentQuestionIndex]

        // Перевіряємо, чи обрана відповідь правильна
        val selectedIndices = answerCheckBoxes
            .mapIndexedNotNull { index, checkBox -> if (checkBox.isChecked) index else null }

        // Перевіряємо, чи хоча б один чекбокс вибраний
        if (selectedIndices.isEmpty()) {
            // Відображаємо повідомлення або виконуємо дії для повідомлення користувачеві
            return
        }

        val isCorrect = currentQuestion.correctAnswerIndices == selectedIndices &&
                selectedIndices.size == currentQuestion.requiredAnswersCount

        if (isCorrect) {
            model.score++
        }

        scoreTextView.text = getString(R.string.score_label, if (model.score < 0) 0 else model.score)

        model.currentQuestionIndex++
        updateQuestion()

        if (model.currentQuestionIndex >= model.questions.size) {
            endGame()
        }
    }

    private fun showResultsDialog() {
        val message = "Your result: ${model.score}"
        val dialog = AlertDialog.Builder(this)
            .setTitle("Result")
            .setMessage(message)
            .setPositiveButton("Restart") { _, _ -> restartGame() }
            .setNegativeButton("Exit") { _, _ -> finish() }
            .setCancelable(false)
            .create()

        dialog.show()
    }

    private fun restartGame() {
        model.currentQuestionIndex = 0
        model.score = 0
        updateQuestion()
        scoreTextView.text = getString(R.string.score_label, model.score)
    }

    private fun endGame() {
        showResultsDialog()
    }
}
