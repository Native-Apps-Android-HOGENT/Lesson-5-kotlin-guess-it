package com.example.android.guesstheword.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(
        val word: String,
        @PrimaryKey(autoGenerate = true)
        var nightId: Long = 0L
)