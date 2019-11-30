package kpk.dev.feature_character_list.datasource.remote

import io.reactivex.Single
import kpk.dev.feature_character_list.datasource.model.CharacterEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface BreakingBadCharacterApi {
    @GET("/api/characters/")
    fun getCharacterList(): Single<List<CharacterEntity>>

    @GET("/api/characters/{id}")
    fun getCharacterById(@Path(value = "id") id: Int): Single<CharacterEntity>
}