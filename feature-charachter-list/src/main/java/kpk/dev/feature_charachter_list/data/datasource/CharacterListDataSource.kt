package kpk.dev.feature_charachter_list.data.datasource

import io.reactivex.Single
import kpk.dev.feature_charachter_list.domain.model.Character

interface ICharactersOfflineDataSource {
    fun getCharachterList(): Single<List<Character>>
    fun putCharachterList(characterList: List<Character>): Single<List<Character>>

    fun getCharacter(id: Int): Single<Character>
    fun putCharachterList(character: Character): Single<Character>
}

interface ICharactersRemoteDataSource {
    fun getCharachterList(): Single<List<Character>>

    fun getCharacter(id: Int): Single<Character>
}