package com.example.android.guesstheword.network


import com.example.android.guesstheword.domain.Word
import retrofit2.http.GET

const val BASE_URL = "http://giveaword.e-quality.be/"

interface WordApiService {

    @GET("rest")
    suspend fun getWords(): List<Word>
}

