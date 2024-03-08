package com.example.wooclamse

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class QuizActivity : AppCompatActivity() {

    private lateinit var questionTitle: TextView
    private lateinit var answersGroup: RadioGroup
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionTitle = findViewById(R.id.question_title)
        answersGroup = findViewById(R.id.answers_group)
        submitButton = findViewById(R.id.submit_button)

        // Fetch the question from the backend
        fetchCurrentQuestion()
    }

    private fun fetchCurrentQuestion() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                /*
                //Call for the reel backend
                val response = RetrofitInstance.api.getCurrentQuestion()
                if (response.isSuccessful && response.body() != null) {
                    val question = response.body()!!
                    withContext(Dispatchers.Main) {
                        updateUIWithQuestion(question)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Failed to fetch question",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Error: ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        */
            // Call the Mock service for testing purposes
            val response = MockQuizService.getCurrentQuestion()
            if (response.isSuccessful && response.body() != null) {
                val question = response.body()!!
                updateUIWithQuestion(question)
            } else {
                Toast.makeText(applicationContext, "Failed to fetch question", Toast.LENGTH_SHORT).show()
            }
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUIWithQuestion(question: Question) {
        questionTitle.text = question.title
        answersGroup.removeAllViews()
        question.answers.forEachIndexed { index, answer ->
            val radioButton = RadioButton(this).apply {
                text = answer
                id = View.generateViewId()
                tag = index
            }
            answersGroup.addView(radioButton)
        }

        submitButton.setOnClickListener {
            val selectedId = answersGroup.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(selectedId)
            val selectedAnswerIndex = radioButton.tag as Int  //index of the answered question
            val answer = Answer(question.id, selectedAnswerIndex)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = RetrofitInstance.api.submitAnswer(answer)
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            Toast.makeText(applicationContext, "Answer submitted!", Toast.LENGTH_SHORT).show()
                            fetchCurrentQuestion() //fetch next question after successful submission
                        } else {
                            Toast.makeText(applicationContext, "Submission failed!", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
