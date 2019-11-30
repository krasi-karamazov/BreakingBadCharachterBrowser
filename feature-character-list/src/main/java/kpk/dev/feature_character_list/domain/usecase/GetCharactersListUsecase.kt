package kpk.dev.feature_character_list.domain.usecase

import kpk.dev.feature_character_list.domain.repository.ICharacterRepository
import javax.inject.Inject

class GetCharactersListUsecase @Inject constructor(private val characterRepository: ICharacterRepository) {

    fun getCharacterList(refreshData: Boolean) = characterRepository.getCharacterList(refreshData)
}