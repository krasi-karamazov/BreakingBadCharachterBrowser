package kpk.dev.feature_character_list.domain.repository

import io.reactivex.Observable
import io.reactivex.Single
import kpk.dev.feature_character_list.domain.model.Character

interface ICharacterRepository {
    fun getCharacterList(refreshData: Boolean): Single<List<Character>>
    fun getCharacter(id: Int, refreshData: Boolean): Single<Character>
    fun searchForCharactersByName(name: String): Observable<List<Character>>
}