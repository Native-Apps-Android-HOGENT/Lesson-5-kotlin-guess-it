package com.example.android.guesstheword.screens.wordoverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.android.guesstheword.domain.Word
import com.example.android.guesstheword.domain.WordRepository

class WordListViewModel internal constructor(wordRepository: WordRepository) : ViewModel() {


    val words : LiveData<List<Word>> = liveData{
        val data = wordRepository.getAllWords()
        emit(data)
    }
}
