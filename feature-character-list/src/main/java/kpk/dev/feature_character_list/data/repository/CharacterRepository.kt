package kpk.dev.feature_character_list.data.repository

import io.reactivex.Single
import kpk.dev.feature_character_list.data.datasource.ICharactersOfflineDataSource
import kpk.dev.feature_character_list.data.datasource.ICharactersRemoteDataSource
import kpk.dev.feature_character_list.domain.model.Character
import kpk.dev.feature_character_list.domain.repository.ICharacterRepository
import javax.inject.Inject

class CharacterRepository @Inject constructor(val charactersRemoteDataSource: ICharactersRemoteDataSource, val charactersOfflineDataSource: ICharactersOfflineDataSource): ICharacterRepository {
    override fun getCharacterList(refreshData: Boolean): Single<List<Character>> {
        return when(refreshData) {
            true -> {
                charactersRemoteDataSource.getCharacterList()
                    .flatMap { charactersOfflineDataSource.putCharacterList(it) }
            }
            false -> {
                charactersOfflineDataSource.getCharacterList()
                    .onErrorResumeNext { getCharacterList(true) }
            }
        }
    }

    override fun getCharacter(id: Int, refreshData: Boolean): Single<Character> {
        return when(refreshData) {
            true -> {
                charactersRemoteDataSource.getCharacter(id)
                    .flatMap { charactersOfflineDataSource.putCharacter(it) }
            }
            false -> {
                charactersOfflineDataSource.getCharacter(id)
                    .onErrorResumeNext { getCharacter(id, true) }
            }
        }
    }
}