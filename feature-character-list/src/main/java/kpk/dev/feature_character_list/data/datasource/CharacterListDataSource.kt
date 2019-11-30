package kpk.dev.feature_character_list.data.datasource

import io.reactivex.Single
import kpk.dev.feature_character_list.domain.model.Character

interface ICharactersOfflineDataSource {
    fun getCharacterList(): Single<List<Character>>
    fun putCharacterList(characterList: List<Character>): Single<List<Character>>

    fun getCharacter(id: Int): Single<Character>
    fun putCharacter(character: Character): Single<Character>
}

interface ICharactersRemoteDataSource {
    fun getCharacterList(): Single<List<Character>>

    fun getCharacter(id: Int): Single<Character>
}