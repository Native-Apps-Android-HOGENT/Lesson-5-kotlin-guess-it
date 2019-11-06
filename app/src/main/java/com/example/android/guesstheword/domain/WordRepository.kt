package com.example.android.guesstheword.domain

import android.net.ConnectivityManager
import com.example.android.guesstheword.database.WordDao
import com.example.android.guesstheword.network.WordApiService

class WordRepository(
        private val wordDao: WordDao,
        private val wordApiService: WordApiService,
        private val connectivityManager: ConnectivityManager) {


    suspend fun getAllWords(): List<Word> {
        if (connectedToInternet()) {
            val words = wordApiService.getWords()
            // Save locally for future offline requests
            saveInLocalDatabase(words)
            return words
        } else {
            return wordDao.getAllWords()
        }
    }

    suspend fun insert(word: Word) = wordDao.insert(word)

    private suspend fun saveInLocalDatabase(words: List<Word>) {
        words.forEach {
            wordDao.insert(it)
        }
    }

    private fun connectedToInternet(): Boolean {
        with(connectivityManager) {
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}