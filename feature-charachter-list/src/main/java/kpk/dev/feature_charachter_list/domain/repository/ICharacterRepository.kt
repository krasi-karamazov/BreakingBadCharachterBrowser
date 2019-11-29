package kpk.dev.feature_charachter_list.domain.repository

import io.reactivex.Single
import kpk.dev.feature_charachter_list.domain.model.Character

interface ICharacterRepository {
    fun getCharacterList(): Single<List<Character>>
    fun getCharacter(id: Int): Single<Character>
}