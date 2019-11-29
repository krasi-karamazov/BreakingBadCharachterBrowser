package kpk.dev.feature_charachter_list.datasource.remote

import io.reactivex.Single
import kpk.dev.feature_charachter_list.data.datasource.ICharactersRemoteDataSource
import kpk.dev.feature_charachter_list.domain.model.Character
import javax.inject.Inject

class CharactersRemoteDataSource @Inject constructor(): ICharactersRemoteDataSource {

    override fun getCharachterList(): Single<List<Character>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCharacter(id: Int): Single<Character> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}