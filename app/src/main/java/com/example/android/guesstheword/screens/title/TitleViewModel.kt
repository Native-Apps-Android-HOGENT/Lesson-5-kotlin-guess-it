package com.example.android.guesstheword.screens.title

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.guesstheword.domain.Word
import com.example.android.guesstheword.domain.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TitleViewModel(private val wordRepository: WordRepository) : ViewModel() {
    val newWord = MutableLiveData<String>()

    fun onAddNewWord() {
        if (!newWord.value.isNullOrBlank())
            viewModelScope.launch {
                val word = Word(newWord.value!!)
                withContext(Dispatchers.IO) {
                    wordRepository.insert(word)
                }
                //Reset input field
                newWord.value = null
            }
    }
}
