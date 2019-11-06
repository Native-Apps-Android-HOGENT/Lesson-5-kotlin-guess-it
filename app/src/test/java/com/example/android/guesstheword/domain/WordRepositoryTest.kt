package com.example.android.guesstheword.domain

import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.android.guesstheword.database.WordDao
import com.example.android.guesstheword.network.WordApiService
import io.mockk.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class WordRepositoryTest {
    private lateinit var repository: WordRepository
    private val wordDao = mockk<WordDao>()
    private val wordApiService = mockk<WordApiService>()
    private val connectivityManager = mockk<ConnectivityManager>()
    private val networkInfo = mockk<NetworkInfo>()

    @Before
    fun setUp() {
        repository = WordRepository(wordDao, wordApiService, connectivityManager)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun wordRepository_getAllWords_noInternet_onlyCallsDatabase() {
        // Arrange
        every { networkInfo.isConnected } returns false
        every { connectivityManager.activeNetworkInfo } returns networkInfo
        coEvery { wordDao.getAllWords() } returns listOf()

        // Act
        runBlockingTest {
            repository.getAllWords()

            coVerify { wordDao.getAllWords() }
            coVerify { wordApiService.getWords() wasNot Called }
        }
    }

    @Test
    fun wordRepository_getAllWords_internetAvailable_callsNetwork() {
        // Arrange
        every { networkInfo.isConnected } returns true
        every { connectivityManager.activeNetworkInfo } returns networkInfo
        val word = Word("test")
        coEvery { wordApiService.getWords() } returns listOf(word)
        coEvery { wordDao.insert(any()) } returns Unit

        // Act
        runBlockingTest {
            repository.getAllWords()

            coVerify { wordApiService.getWords() }
        }
    }

    @Test
    fun wordRepository_getAllWords_internetAvailable_savesNetworkCallInDatabase() {
        // Arrange
        every { networkInfo.isConnected } returns true
        every { connectivityManager.activeNetworkInfo } returns networkInfo
        val word = Word("test")
        coEvery { wordApiService.getWords() } returns listOf(word)
        coEvery { wordDao.insert(any()) } returns Unit

        // Act
        runBlockingTest {
            repository.getAllWords()

            coVerify { wordDao.insert(word) }
        }
    }
}