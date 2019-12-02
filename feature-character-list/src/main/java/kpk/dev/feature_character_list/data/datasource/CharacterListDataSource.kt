package kpk.dev.feature_character_list.data.datasource

import io.reactivex.Observable
import io.reactivex.Single
import kpk.dev.feature_character_list.domain.model.Character

interface ICharactersOfflineDataSource {
    fun getCharacterList(): Single<List<Character>>
    fun putCharacterList(characterList: List<Character>): Single<List<Character>>

    fun getCharacter(id: Int): Single<Character>
    fun searchForCharactersByName(name: String): Observable<List<Character>>
    fun filterCharactersBySeasonApperances(appearances: List<Int>): Observable<List<Character>>
    fun putCharacter(character: Character): Single<Character>
}

interface ICharactersRemoteDataSource {
    fun getCharacterList(): Single<List<Character>>

    fun getCharacter(id: Int): Single<Character>
}