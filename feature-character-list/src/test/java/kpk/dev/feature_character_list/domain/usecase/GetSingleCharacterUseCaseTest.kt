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

class GetSingleCharacterUseCaseTest {
    private lateinit var useCase: GetSingleCharacterUseCase

    private val repository: ICharacterRepository = mock()

    private val dataItem = character.copy(name = "Brockie Online")

    private val error = Throwable()

    @Before
    fun setUp() {
        useCase = GetSingleCharacterUseCase(repository)
    }

    @Test
    fun `get single character from repository to success`() {
        whenever(repository.getCharacter(24, false)).thenReturn(Single.just(dataItem))

        val test = useCase.getCharacter(24, false).test()

        verify(repository).getCharacter(24, false)

        test.assertComplete()
        test.assertNoErrors()
        test.assertValueCount(1)
        test.assertValue(dataItem)
    }

    @Test
    fun `get single character from repository to failure`() {
        whenever(repository.getCharacter(24, false)).thenReturn(Single.error(error))

        val test = useCase.getCharacter(24, false).test()

        verify(repository).getCharacter(24, false)

        test.assertNotComplete()
        test.assertError(error)
        test.assertNoValues()
    }
}