package kpk.dev.feature_character_list.datasource.remote

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import kpk.dev.feature_character_list.characterEntity
import kpk.dev.feature_character_list.data.datasource.ICharactersRemoteDataSource
import kpk.dev.feature_character_list.datasource.model.mapEntityToDomain
import org.junit.Before
import org.junit.Test

class CharactersRemoteDataSourceTest {

    private lateinit var dataSource: ICharactersRemoteDataSource

    private val remoteDataItem = characterEntity.copy(name = "Brockie online")

    private val remoteDataList = listOf(remoteDataItem)

    private val error = Throwable()

    private val mockApi: BreakingBadCharacterApi = mock()

    @Before
    fun setUp() {
        dataSource = CharactersRemoteDataSource(mockApi)
    }

    @Test
    fun `get character list from api to success`() {
        whenever(mockApi.getCharacterList()).thenReturn(Single.just(remoteDataList))

        val test = dataSource.getCharacterList().test()

        verify(mockApi).getCharacterList()

        test.assertValue(remoteDataList.mapEntityToDomain())
    }

    @Test
    fun `get character list from api to failure`() {
        whenever(mockApi.getCharacterList()).thenReturn(Single.error(error))

        val test = dataSource.getCharacterList().test()

        verify(mockApi).getCharacterList()

        test.assertError(error)
    }

    @Test
    fun `get single character from api to success`() {
        whenever(mockApi.getCharacterById(24)).thenReturn(Single.just(remoteDataItem))

        val test = dataSource.getCharacter(24).test()

        verify(mockApi).getCharacterById(24)

        test.assertValue(remoteDataItem.mapEntityToDomain())
    }

    @Test
    fun `get single character from api to failure`() {
        whenever(mockApi.getCharacterById(24)).thenReturn(Single.error(error))

        val test = dataSource.getCharacter(24).test()

        verify(mockApi).getCharacterById(24)

        test.assertError(error)
    }
}