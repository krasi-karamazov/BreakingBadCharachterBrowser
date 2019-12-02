package kpk.dev.feature_character_list.datasource.cache

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

    private val key = "characters"

    private lateinit var dataSource: ICharactersOfflineDataSource
    private val mockPaperCache: PaperCache<List<Character>> = mock()

    private val cachedDataItem = character.copy(name = "Brockie offline")
    private val remoteDataItem = character.copy(name = "Brockie online")

    private val cachedDataList = listOf(cachedDataItem)
    private val remoteDataList = listOf(remoteDataItem)

    private val searchDataItemWalter = character.copy(name = "Walter")
    private val searchDataItemJesse = character.copy(name = "Jesse")
    private val searchDataItemMarie = character.copy(name = "Marie")
    private val searchDataItemWalterJr = character.copy(name = "Walter Jr")

    private val searchList = listOf(searchDataItemWalter, searchDataItemJesse, searchDataItemMarie, searchDataItemWalterJr)

    private val filterDataItemSeasons123 = character.copy(name = "Person 1", appearance = listOf(1, 2, 3))
    private val filterDataItemSeasons234 = character.copy(name = "Person 2", appearance = listOf(2, 3, 4))
    private val filterDataItemSeasons1 = character.copy(name = "Person 3", appearance = listOf(1))
    private val filterDataItemSeasons45 = character.copy(name = "Person 4", appearance = listOf(4, 5))

    private val filterList = listOf(filterDataItemSeasons123, filterDataItemSeasons234, filterDataItemSeasons1, filterDataItemSeasons45)

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
        test.assertValue(remoteDataItem)
    }

    @Test
    fun `put single character in cache to failure`() {
        whenever(mockPaperCache.get(key)).thenReturn(Single.error(error))

        val test = dataSource.putCharacter(remoteDataItem).test()
        verify(mockPaperCache).get(key)
        test.assertError(error)
    }

    //Search tests
    @Test
    fun `search characters by partial name`() {
        whenever(mockPaperCache.get(key)).thenReturn(Single.just(searchList))

        val test = dataSource.searchForCharactersByName("wa").test()

        verify(mockPaperCache).get(key)
        test.assertValue(listOf(searchDataItemWalter, searchDataItemWalterJr))
    }

    @Test
    fun `search characters by full name`() {
        whenever(mockPaperCache.get(key)).thenReturn(Single.just(searchList))

        val test = dataSource.searchForCharactersByName("Jesse").test()

        verify(mockPaperCache).get(key)
        test.assertValue(listOf(searchDataItemJesse))
    }

    @Test
    fun `search characters fail to empty`() {
        whenever(mockPaperCache.get(key)).thenReturn(Single.just(searchList))

        val test = dataSource.searchForCharactersByName("dfsdfsdf").test()

        verify(mockPaperCache).get(key)
        test.assertValue(listOf())
    }

    @Test
    fun `search characters by empty string to full list`() {
        whenever(mockPaperCache.get(key)).thenReturn(Single.just(searchList))

        val test = dataSource.searchForCharactersByName("").test()

        verify(mockPaperCache).get(key)
        test.assertValue(searchList)
    }

    //Filter tests
    @Test
    fun `filter characters for season 1 only return 2 items`() {
        whenever(mockPaperCache.get(key)).thenReturn(Single.just(filterList))

        val test = dataSource.filterCharactersBySeasonApperances(listOf(1)).test()

        verify(mockPaperCache).get(key)
        test.assertValue(listOf(filterDataItemSeasons123, filterDataItemSeasons1))
    }

    @Test
    fun `filter characters for season 1 2 3 return 1 item`() {
        whenever(mockPaperCache.get(key)).thenReturn(Single.just(filterList))

        val test = dataSource.filterCharactersBySeasonApperances(listOf(1, 2, 3)).test()

        verify(mockPaperCache).get(key)
        test.assertValue(listOf(filterDataItemSeasons123))
    }

    @Test
    fun `filter characters for season 2 3 return 2 item`() {
        whenever(mockPaperCache.get(key)).thenReturn(Single.just(filterList))

        val test = dataSource.filterCharactersBySeasonApperances(listOf(2, 3)).test()

        verify(mockPaperCache).get(key)
        test.assertValue(listOf(filterDataItemSeasons123, filterDataItemSeasons234))
    }
}