package kpk.dev.feature_charachter_list.data.repository

import io.reactivex.Single
import kpk.dev.feature_charachter_list.domain.model.Character
import kpk.dev.feature_charachter_list.domain.repository.ICharacterRepository
import javax.inject.Inject

class CharacterRepository @Inject constructor(): ICharacterRepository {
    override fun getCharacterList(): Single<List<Character>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCharacter(id: Int): Single<Character> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}