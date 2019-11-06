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
            val onlineWords = wordApiService.getWords()
            val offlineWords = wordDao.getWords()
            // Save locally for future offline requests
            saveInLocalDatabase(onlineWords)
            return onlineWords + offlineWords
        } else {
            return wordDao.getWords()
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