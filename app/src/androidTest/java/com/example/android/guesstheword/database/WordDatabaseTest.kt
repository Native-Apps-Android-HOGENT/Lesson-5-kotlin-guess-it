package com.example.android.guesstheword.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.guesstheword.domain.Word
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class WordDatabaseTest {

    private lateinit var wordDao: WordDao
    private lateinit var db: WordDatabase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory wordDao because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, WordDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
        wordDao = db.wordDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetWord() {
        // We have to specify the ID here or we can't test for object equality in assert due to
        // autoGeneration of the ID
        val word = Word("Test", 1L)
        runBlocking{
            wordDao.insert(word)
            val allWords = wordDao.getAllWords()
            assertTrue(allWords.contains(word))
        }
    }
}