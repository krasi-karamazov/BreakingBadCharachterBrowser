package kpk.dev.feature_character_list.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import kpk.dev.feature_character_list.character
import kpk.dev.feature_character_list.data.datasource.ICharactersOfflineDataSource
import kpk.dev.feature_character_list.data.datasource.ICharactersRemoteDataSource
import org.junit.Before

import org.junit.Test

class CharacterRepositoryTest {

    private lateinit var repository: CharacterRepository

    private val mockCharacterRemoteDataSource: ICharactersRemoteDataSource = mock()
    private val mockCharacterOfflineDataSource: ICharactersOfflineDataSource = mock()

    private val cachedDataItem = character.copy(name = "Brockie offline")
    private val remoteDataItem = character.copy(name = "Brockie online")

    private val cachedDataList = listOf(cachedDataItem)
    private val remoteDataList = listOf(remoteDataItem)

    private val error = Throwable()

    @Before
    fun setUp() {
        repository = CharacterRepository(mockCharacterRemoteDataSource, mockCharacterOfflineDataSource)
    }

    //Character list tests
    @Test
    fun `get characters list from cache is success`() {
        whenever(mockCharacterOfflineDataSource.getCharacterList()).thenReturn(Single.just(cachedDataList))

        val testResult = repository.getCharacterList(false).test()

        verify(mockCharacterOfflineDataSource).getCharacterList()
        testResult.assertValue(cachedDataList)
    }

    @Test
    fun `get characters list from cache is failure, time to download to success`() {
        whenever(mockCharacterOfflineDataSource.getCharacterList()).thenReturn(Single.error(error))
        whenever(mockCharacterRemoteDataSource.getCharacterList()).thenReturn(Single.just(remoteDataList))
        whenever(mockCharacterOfflineDataSource.putCharacterList(remoteDataList)).thenReturn(Single.just(remoteDataList))

        val testResult = repository.getCharacterList(false).test()

        verify(mockCharacterOfflineDataSource).getCharacterList()
        verify(mockCharacterRemoteDataSource).getCharacterList()
        verify(mockCharacterOfflineDataSource).putCharacterList(remoteDataList)
        testResult.assertValue(remoteDataList)
    }

    @Test
    fun `get characters list from remote is success`() {
        whenever(mockCharacterRemoteDataSource.getCharacterList()).thenReturn(Single.just(remoteDataList))
        whenever(mockCharacterOfflineDataSource.putCharacterList(remoteDataList)).thenReturn(Single.just(remoteDataList))

        val testResult = repository.getCharacterList(true).test()

        verify(mockCharacterRemoteDataSource).getCharacterList()
        verify(mockCharacterOfflineDataSource).putCharacterList(remoteDataList)
        testResult.assertValue(remoteDataList)
    }

    @Test
    fun `get characters list from remote is failure`() {
        whenever(mockCharacterRemoteDataSource.getCharacterList()).thenReturn(Single.error(error))

        val testResult = repository.getCharacterList(true).test()

        verify(mockCharacterRemoteDataSource).getCharacterList()

        testResult.assertError(error)
    }

    //Single character test
    @Test
    fun `get single character from cache is success`() {
        whenever(mockCharacterOfflineDataSource.getCharacter(24)).thenReturn(Single.just(cachedDataItem))

        val testResult = repository.getCharacter(24, false).test()

        verify(mockCharacterOfflineDataSource).getCharacter(24)
        testResult.assertValueCount(1)
        testResult.assertValue(cachedDataItem)
    }

    @Test
    fun `get single character from cache is failure, time to download to success`() {
        whenever(mockCharacterOfflineDataSource.getCharacter(24)).thenReturn(Single.error(error))
        whenever(mockCharacterRemoteDataSource.getCharacter(24)).thenReturn(Single.just(remoteDataItem))
        whenever(mockCharacterOfflineDataSource.putCharacter(remoteDataItem)).thenReturn(Single.just(remoteDataItem))

        val testResult = repository.getCharacter(24,false).test()

        verify(mockCharacterOfflineDataSource).getCharacter(24)
        verify(mockCharacterRemoteDataSource).getCharacter(24)
        verify(mockCharacterOfflineDataSource).putCharacter(remoteDataItem)
        testResult.assertValue(remoteDataItem)
    }

    @Test
    fun `get single character from remote is success`() {
        whenever(mockCharacterRemoteDataSource.getCharacter(24)).thenReturn(Single.just(remoteDataItem))
        whenever(mockCharacterOfflineDataSource.putCharacter(remoteDataItem)).thenReturn(Single.just(remoteDataItem))

        val testResult = repository.getCharacter(24, true).test()

        verify(mockCharacterRemoteDataSource).getCharacter(24)
        verify(mockCharacterOfflineDataSource).putCharacter(remoteDataItem)
        testResult.assertValue(remoteDataItem)
    }

    @Test
    fun `get single character from remote is failure`() {
        whenever(mockCharacterRemoteDataSource.getCharacter(24)).thenReturn(Single.error(error))

        val testResult = repository.getCharacter(24, true).test()

        verify(mockCharacterRemoteDataSource).getCharacter(24)

        testResult.assertError(error)
    }
}