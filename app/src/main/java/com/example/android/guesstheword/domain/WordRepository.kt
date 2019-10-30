package com.example.android.guesstheword.domain

import android.content.Context
import com.example.android.guesstheword.database.WordDao
import com.example.android.guesstheword.database.WordDatabase

// Implementing a Repository allows us to freely add a Network layer later on, without changing
// anything for the classes using the repo.
class WordRepository(context: Context) {

    private val wordDatabase = WordDatabase.getInstance(context)
    private val wordDao: WordDao = wordDatabase.wordDao

    suspend fun getAllWords() = wordDao.getAllWords()

    suspend fun insert(word: Word) = wordDao.insert(word)
}