package kpk.dev.feature_character_list.datasource.remote

import io.reactivex.Single
import kpk.dev.feature_character_list.data.datasource.ICharactersRemoteDataSource
import kpk.dev.feature_character_list.datasource.model.mapEntityToDomain
import kpk.dev.feature_character_list.domain.model.Character
import javax.inject.Inject

class CharactersRemoteDataSource @Inject constructor(private val api: BreakingBadCharacterApi): ICharactersRemoteDataSource {

    override fun getCharacterList(): Single<List<Character>> =
        api.getCharacterList().map { it.mapEntityToDomain() }

    override fun getCharacter(id: Int): Single<Character> =
        api.getCharacterById(id).map { it.mapEntityToDomain() }
}