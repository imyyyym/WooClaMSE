package com.example.wooclamse

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface QuizApiService {
    @GET("questions/current")
    suspend fun getCurrentQuestion(): Response<Question>
    @POST("submitAnswer")
    suspend fun submitAnswer(@Body answer: Answer): retrofit2.Response<Void>
}
