package com.example.android.guesstheword.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.guesstheword.domain.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {
    abstract val wordDao: WordDao


    companion object {
        @Volatile
        private var INSTANCE: WordDatabase? = null

        fun getInstance(context: Context): WordDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            WordDatabase::class.java,
                            "word_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    instance.populateInitialData()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

    private fun populateInitialData() {
        val wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                wordList.map { Word(it) }
                        .map { wordDao.insert(it) }
            }
        }
    }
}
