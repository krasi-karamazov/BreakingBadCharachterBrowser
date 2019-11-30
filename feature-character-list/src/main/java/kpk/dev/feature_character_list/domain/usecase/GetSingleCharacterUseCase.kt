package kpk.dev.feature_character_list.domain.usecase

import kpk.dev.feature_character_list.domain.repository.ICharacterRepository
import javax.inject.Inject

class GetSingleCharacterUseCase @Inject constructor(private val characterRepository: ICharacterRepository) {

    fun getCharacter(id: Int, refresh: Boolean) = characterRepository.getCharacter(id, refresh)
}