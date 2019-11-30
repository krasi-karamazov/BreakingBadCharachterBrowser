package kpk.dev.feature_character_list.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import kpk.dev.feature_character_list.character
import kpk.dev.feature_character_list.domain.repository.ICharacterRepository
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class GetCharactersListUseCaseTest {

    private lateinit var useCase: GetCharactersListUseCase

    private val repository: ICharacterRepository = mock()

    private val dataItem = character.copy(name = "Brockie Online")
    private val dataList = listOf(dataItem)

    private val error = Throwable()

    @Before
    fun setUp() {
        useCase = GetCharactersListUseCase(repository)
    }

    @Test
    fun `get character list from repository to success`() {
        whenever(repository.getCharacterList(false)).thenReturn(Single.just(dataList))

        val test = useCase.getCharacterList(false).test()

        verify(repository).getCharacterList(false)

        test.assertValueCount(1)
        test.assertComplete()
        test.assertNoErrors()
        test.assertValue(dataList)
    }

    @Test
    fun `get character list from repository to failure`() {
        whenever(repository.getCharacterList(false)).thenReturn(Single.error(error))

        val test = useCase.getCharacterList(false).test()

        verify(repository).getCharacterList(false)

        test.assertNotComplete()
        test.assertError(error)
        test.assertNoValues()
    }
}