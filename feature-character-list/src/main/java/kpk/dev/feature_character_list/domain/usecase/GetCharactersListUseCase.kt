package kpk.dev.feature_character_list.domain.usecase

import kpk.dev.feature_character_list.data.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor(private val characterRepository: CharacterRepository) {

    fun getCharacterList(refreshData: Boolean) = characterRepository.getCharacterList(refreshData)
}