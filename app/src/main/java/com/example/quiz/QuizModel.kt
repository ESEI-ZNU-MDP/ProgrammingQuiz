// QuizModel.kt
package com.example.quiz

class QuizModel {
    var currentQuestionIndex = 0
    var score = 0
    val questions: List<Question> = generateQuestions()

    private fun generateQuestions(): List<Question> {
        return listOf(
            Question("Which programming language is the official language for Android development?", listOf("Java", "Kotlin", "C++", "Python"), listOf(1), 1),
            Question("What does the HTML abbreviation stand for?", listOf("Hyperlink and Text Markup Language", "Hyper Transfer Markup Language", "HyperText Markup Language", "High-Level Text Markup Language"), listOf(2), 1),
            Question("Which programming language is associated with creating web pages?", listOf("Java", "JavaScript", "C#", "Swift"), listOf(1), 1),
            Question("Which company developed the C# programming language?", listOf("Microsoft", "Apple", "Google", "Oracle"), listOf(0), 1),
            Question("Which of the listed programming languages are functional?", listOf("Java", "Haskell", "Python", "Scala"), listOf(1, 3), 2),
            Question("Which of the listed programming paradigms belongs to logical programming?", listOf("Procedural Programming", "Object-Oriented Programming", "Functional Programming", "Logical Programming"), listOf(3), 1),
            Question("Which of the SOLID principles listed are related to inheritance?", listOf("Single Responsibility Principle", "Open/Closed Principle", "Liskov Substitution Principle", "Dependency Inversion Principle"), listOf(2), 1),
            Question("Which of the listed data structures are immutable?", listOf("List", "Array", "Queue", "Tuple"), listOf(0, 3), 2),
            Question("Which of the listed algorithms are related to sorting?", listOf("Binary Search", "Dijkstra's Algorithm", "Bubble Sort", "Hash Function"), listOf(2), 1)
        )
    }
}
