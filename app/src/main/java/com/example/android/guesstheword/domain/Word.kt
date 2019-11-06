package com.example.android.guesstheword.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "word_table")
data class Word(
        @Json(name = "description")
        val word: String,
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0L
)