package kpk.dev.feature_character_list.datasource.cache

import android.util.Log
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import kpk.dev.caching.PaperCache
import kpk.dev.feature_character_list.character
import kpk.dev.feature_character_list.data.datasource.ICharactersOfflineDataSource
import kpk.dev.feature_character_list.domain.model.Character
import org.junit.Before
import org.junit.Test

class CharactersOfflineDataSourceTest {

    val key = "characters"

    private lateinit var dataSource: ICharactersOfflineDataSource
    private val mockPaperCache: PaperCache<List<Character>> = mock()

    private val cachedDataItem = character.copy(name = "Brockie offline")
    private val remoteDataItem = character.copy(name = "Brockie online")

    private val cachedDataList = listOf(cachedDataItem)
    private val remoteDataList = listOf(remoteDataItem)

    private val error = Throwable()

    @Before
    fun setUp() {
        dataSource = CharactersOfflineDataSource(mockPaperCache)
    }

    //Getting info tests

    //Getting character list
    @Test
    fun `get character list from cache to success`() {
        whenever(mockPaperCache.get(key)).thenReturn(Single.just(cachedDataList))

        val test = dataSource.getCharacterList().test()
        verify(mockPaperCache).get(key)
        test.assertValue(cachedDataList)
    }

    @Test
    fun `get character list from cache to failure`() {
        whenever(mockPaperCache.get(key)).thenReturn(Single.error(error))

        val test = dataSource.getCharacterList().test()
        verify(mockPaperCache).get(key)
        test.assertError(error)
    }

    //Getting single character
    @Test
    fun `get single character to success`() {
        whenever(mockPaperCache.get(key)).thenReturn(Single.just(cachedDataList))

        val test = dataSource.getCharacter(24).test()
        verify(mockPaperCache).get(key)
        test.assertValue(cachedDataItem)
    }

    @Test
    fun `get single character to fail`() {
        whenever(mockPaperCache.get(key)).thenReturn(Single.error(error))

        val test = dataSource.getCharacter(24).test()
        verify(mockPaperCache).get(key)
        test.assertError(error)
    }

    //Saving info tests

    //Saving character list
    @Test
    fun `put character list in cache to success`() {
        whenever(mockPaperCache.put(key, remoteDataList)).thenReturn(Single.just(remoteDataList))

        val test = dataSource.putCharacterList(remoteDataList).test()
        verify(mockPaperCache).put(key, remoteDataList)
        test.assertValue(remoteDataList)
    }

    @Test
    fun `put character list in cache to failure`() {
        whenever(mockPaperCache.put(key, remoteDataList)).thenReturn(Single.error(error))

        val test = dataSource.putCharacterList(remoteDataList).test()
        verify(mockPaperCache).put(key, remoteDataList)
        test.assertError(error)
    }

    //Saving single character
    @Test
    fun `put single character in cache to success`() {
        whenever(mockPaperCache.get(key)).thenReturn(Single.just(cachedDataList))

        val test = dataSource.putCharacter(remoteDataItem).test()
        verify(mockPaperCache).get(key)
        Log.d("Test", "Test")
        //test.assertValue(remoteDataItem)
    }

    @Test
    fun `put single character in cache to failure`() {
        whenever(mockPaperCache.get(key)).thenReturn(Single.error(error))

        val test = dataSource.putCharacter(remoteDataItem).test()
        verify(mockPaperCache).get(key)
        test.assertError(error)
    }
}