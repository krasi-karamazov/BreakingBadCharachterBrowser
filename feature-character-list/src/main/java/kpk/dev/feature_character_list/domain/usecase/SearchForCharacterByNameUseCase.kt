package kpk.dev.feature_character_list.domain.usecase

import io.reactivex.Observable
import kpk.dev.feature_character_list.domain.model.Character
import kpk.dev.feature_character_list.domain.repository.ICharacterRepository
import javax.inject.Inject

class SearchForCharacterByNameUseCase @Inject constructor(private val characterRepository: ICharacterRepository) {

    fun searchForCharacterByName(name: String): Observable<List<Character>> =
        characterRepository.searchForCharactersByName(name)
}