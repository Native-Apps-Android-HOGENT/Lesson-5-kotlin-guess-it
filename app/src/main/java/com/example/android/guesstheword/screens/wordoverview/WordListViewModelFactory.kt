package com.example.android.guesstheword.screens.wordoverview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.guesstheword.domain.WordRepository

class WordListViewModelFactory(
        private val wordRepository: WordRepository) : ViewModelProvider.NewInstanceFactory()
{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WordListViewModel(wordRepository) as T
    }
}