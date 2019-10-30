package com.example.android.guesstheword.screens.title

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.guesstheword.domain.Word
import com.example.android.guesstheword.domain.WordRepository
import kotlinx.coroutines.launch

class TitleViewModel(private val wordRepository: WordRepository) : ViewModel() {
    val newWord = MutableLiveData<String>()

    fun onAddNewWord() {
        if (!newWord.value.isNullOrBlank())
            viewModelScope.launch {
                val word = Word(newWord.value!!)
                wordRepository.insert(word)
                //Reset input field
                newWord.value = null
            }
    }
}
