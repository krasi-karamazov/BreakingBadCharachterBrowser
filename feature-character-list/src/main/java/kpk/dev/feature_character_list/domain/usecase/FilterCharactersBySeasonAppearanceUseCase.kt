package kpk.dev.feature_character_list.domain.usecase

import io.reactivex.Observable
import kpk.dev.feature_character_list.domain.model.Character
import kpk.dev.feature_character_list.domain.repository.ICharacterRepository
import javax.inject.Inject

class FilterCharactersBySeasonAppearanceUseCase @Inject constructor(private val characterRepository: ICharacterRepository) {

    fun filterCharactersBySeasonAppearance(appearances: List<Int>): Observable<List<Character>> =
        characterRepository.filterCharactersBySeasonAppearance(appearances)
}