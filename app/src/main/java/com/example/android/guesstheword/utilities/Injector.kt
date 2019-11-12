package com.example.android.guesstheword.utilities

import android.content.Context
import android.net.ConnectivityManager
import com.example.android.guesstheword.database.WordDatabase
import com.example.android.guesstheword.domain.WordRepository
import com.example.android.guesstheword.network.WordApi
import com.example.android.guesstheword.screens.wordoverview.WordListViewModelFactory

object Injector {

     fun provideWordRepository(context: Context): WordRepository{
         val wordApiService = WordApi.retrofitService
         val wordDao = WordDatabase.getInstance(context).wordDao
         val connectivityManager = getConnectivityManager(context)
         return WordRepository(wordDao,wordApiService,connectivityManager)
     }

     private fun getConnectivityManager(context: Context): ConnectivityManager{
         return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
     }

    fun provideWordListViewModelFactory(context: Context) : WordListViewModelFactory{
        val repository = provideWordRepository(context)
        return WordListViewModelFactory(repository)
    }



}