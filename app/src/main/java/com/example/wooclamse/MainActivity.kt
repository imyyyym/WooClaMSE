package com.example.wooclamse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startQuizButton: Button = findViewById(R.id.start_quiz_button)
        startQuizButton.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}