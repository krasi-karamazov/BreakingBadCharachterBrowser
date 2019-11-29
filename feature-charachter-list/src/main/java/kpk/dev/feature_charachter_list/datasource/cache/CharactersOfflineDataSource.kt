package kpk.dev.feature_charachter_list.datasource.cache

import io.reactivex.Single
import kpk.dev.feature_charachter_list.data.datasource.ICharactersOfflineDataSource
import kpk.dev.feature_charachter_list.domain.model.Character

class CharactersOfflineDataSource: ICharactersOfflineDataSource {

    override fun getCharachterList(): Single<List<Character>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun putCharachterList(characterList: List<Character>): Single<List<Character>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCharacter(id: Int): Single<Character> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun putCharachterList(character: Character): Single<Character> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}