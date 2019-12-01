package kpk.dev.feature_character_list.presentation.model

import kpk.dev.feature_character_list.domain.model.Character

data class CharacterItem(
    val appearance: List<Int>,
    val betterCallSaulAppearance: List<Int>,
    val birthday: String,
    val category: String,
    val charId: Int,
    val img: String,
    val name: String,
    val nickname: String,
    val occupation: List<String>,
    val portrayed: String,
    val status: String)

fun List<Character>.mapToPresentation(): List<CharacterItem> = map { CharacterItem(it.appearance, it.betterCallSaulAppearance, it.birthday, it.category, it.charId, it.img, it.name, it.nickname, it.occupation, it.portrayed, it.status) }

fun Character.mapToPresentation(): CharacterItem = CharacterItem(
    appearance,
    betterCallSaulAppearance,
    birthday,
    category,
    charId,
    img,
    name,
    nickname,
    occupation,
    portrayed,
    status
)