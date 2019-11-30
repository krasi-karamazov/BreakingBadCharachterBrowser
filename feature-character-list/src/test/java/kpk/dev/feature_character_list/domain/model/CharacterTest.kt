package kpk.dev.feature_character_list.domain.model

import kpk.dev.feature_character_list.characterEntity
import kpk.dev.feature_character_list.datasource.model.mapEntityToDomain
import org.junit.Assert.*
import org.junit.Test

class CharacterTest {

    @Test
    fun `map character entity to domain model`() {
        val character = characterEntity.mapEntityToDomain()

        assertEquals(character.charId, characterEntity.charId)
        assertEquals(character.name, characterEntity.name)
        assertEquals(character.appearance, characterEntity.appearance)
        assertEquals(character.betterCallSaulAppearance, characterEntity.betterCallSaulAppearance)
        assertEquals(character.birthday, characterEntity.birthday)
        assertEquals(character.category, characterEntity.category)
        assertEquals(character.img, characterEntity.img)
        assertEquals(character.nickname, characterEntity.nickname)
        assertEquals(character.occupation, characterEntity.occupation)
        assertEquals(character.portrayed, characterEntity.portrayed)
        assertEquals(character.status, characterEntity.status)
    }
}