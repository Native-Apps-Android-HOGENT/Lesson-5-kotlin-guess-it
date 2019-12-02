package com.example.android.guesstheword.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.android.guesstheword.database.WordDatabase
import com.example.android.guesstheword.domain.WordRepository
import com.example.android.guesstheword.network.BASE_URL
import com.example.android.guesstheword.network.WordApiService
import com.example.android.guesstheword.screens.game.GameViewModel
import com.example.android.guesstheword.screens.title.TitleViewModel
import com.example.android.guesstheword.screens.wordoverview.WordListViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val mainModule = module {

    single {
        Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build() as Moshi
    }

    single {
        Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl(BASE_URL)
                .build() as Retrofit
    }

    single { provideWordApiService(get()) }

    single { WordDatabase.getInstance(get()).wordDao }

    single { androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }

    single { WordRepository(get(), get(), get()) }

    viewModel { GameViewModel(get()) }
    viewModel { TitleViewModel(get()) }
    viewModel { WordListViewModel(get()) }
}

private fun provideWordApiService(retrofit: Retrofit): WordApiService {
    return retrofit.create(WordApiService::class.java)
}
