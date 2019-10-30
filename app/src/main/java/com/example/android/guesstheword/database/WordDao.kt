package com.example.android.guesstheword.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.android.guesstheword.domain.Word

@Dao
interface WordDao {

    @Insert
    suspend fun insert(word: Word)

    @Query("SELECT * FROM word_table")
    fun getAllWords(): LiveData<List<Word>>
}