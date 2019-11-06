package com.example.android.guesstheword.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.guesstheword.domain.Word

@Dao
interface WordDao {

    // When not specified Room will crash when trying to enter a Word twice.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: Word)

    @Query("SELECT * FROM word_table")
    suspend fun getWords(): List<Word>
}