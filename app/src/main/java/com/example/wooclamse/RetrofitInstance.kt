package com.example.wooclamse

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: QuizApiService by lazy {
        Retrofit.Builder()
            .baseUrl("YOUR_BACKEND_BASE_URL") //Replace with the real backend base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizApiService::class.java)
    }
}
