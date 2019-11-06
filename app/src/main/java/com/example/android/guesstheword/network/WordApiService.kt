package com.example.android.guesstheword.network


import com.example.android.guesstheword.domain.Word
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://giveaword.e-quality.be/"

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

interface WordApiService {

    @GET("rest")
    suspend fun getWords(): List<Word>
}

object WordApi {
    val retrofitService: WordApiService by lazy {
        retrofit.create(WordApiService::class.java)
    }
}
