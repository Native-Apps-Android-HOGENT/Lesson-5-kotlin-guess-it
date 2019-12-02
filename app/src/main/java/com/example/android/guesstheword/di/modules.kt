package com.example.android.guesstheword.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.android.guesstheword.database.WordDatabase
import com.example.android.guesstheword.domain.WordRepository
import com.example.android.guesstheword.network.WordApi
import com.example.android.guesstheword.screens.game.GameViewModel
import com.example.android.guesstheword.screens.title.TitleViewModel
import com.example.android.guesstheword.screens.wordoverview.WordListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single { WordDatabase.getInstance(get()).wordDao }
    single { WordApi.retrofitService }

    single { androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }

    single { WordRepository(get(), get(), get()) }

    viewModel { GameViewModel(get()) }
    viewModel { TitleViewModel(get()) }
    viewModel { WordListViewModel(get()) }
}