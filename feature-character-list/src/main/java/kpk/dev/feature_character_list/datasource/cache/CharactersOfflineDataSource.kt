package kpk.dev.feature_character_list.datasource.cache

import io.reactivex.Single
import kpk.dev.caching.PaperCache
import kpk.dev.feature_character_list.data.datasource.ICharactersOfflineDataSource
import kpk.dev.feature_character_list.domain.model.Character
import javax.inject.Inject

class CharactersOfflineDataSource @Inject constructor(val cache: PaperCache<List<Character>>): ICharactersOfflineDataSource {
    val key = "characters"

    override fun getCharacterList(): Single<List<Character>> = cache.get(key)

    override fun putCharacterList(characterList: List<Character>): Single<List<Character>> = cache.put(key, characterList)

    override fun getCharacter(id: Int): Single<Character> = cache.get(key).map { it -> it.first{ it.charId == id} }

    override fun putCharacter(character: Character): Single<Character> = cache.get(key)
        .map { it -> it.filter { it.charId == character.charId }.plus(character) }
        .flatMap { list ->
            putCharacterList(list)
        }
        .map { character }
}