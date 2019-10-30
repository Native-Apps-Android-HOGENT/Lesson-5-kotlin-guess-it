package com.example.android.guesstheword.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.android.guesstheword.domain.Word

@Dao
interface WordDao {

    @Insert
    suspend fun insert(word: Word)

    @Query("SELECT * FROM word_table")
    suspend fun getAllWords(): List<Word>
}