package com.example.android.guesstheword.domain

import com.example.android.guesstheword.database.WordDao

// Implementing a Repository allows us to freely add a Network layer later on, without changing
// anything for the classes using the repo.
class WordRepository(private val wordDao: WordDao) {

    fun getAllWords() = wordDao.getAllWords()
    fun insert(word: Word) = wordDao.insert(word)
}