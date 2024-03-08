package com.example.wooclamse

import retrofit2.Response

//Mock service to stimulate fetching questions in order to test the app
object MockQuizService {
    // Predefined list of questions like backend
    private val questions = listOf(
        Question(
            id = 1,
            title = "What is the capital of France?",
            answers = arrayOf("Paris", "London", "Berlin", "Madrid"),
            correctAnswerIndex = 0
        ),
        Question(
            id = 2,
            title = "What is the largest ocean?",
            answers = arrayOf("Atlantic", "Indian", "Arctic", "Pacific"),
            correctAnswerIndex = 3
        )
    )

    fun getCurrentQuestion(): Response<Question> {
        //Simulate fetching a random question from the backend
        val randomQuestion = questions.shuffled().first()
        return Response.success(randomQuestion)
    }
}
