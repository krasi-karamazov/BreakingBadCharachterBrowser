package kpk.dev.feature_character_list.datasource.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kpk.dev.feature_character_list.domain.model.Character

@JsonClass(generateAdapter = true)
data class CharacterEntity(
    @Json(name = "appearance")
    val appearance: List<Int>,
    @Json(name = "better_call_saul_appearance")
    val betterCallSaulAppearance: List<Int>,
    @Json(name = "birthday")
    val birthday: String,
    @Json(name = "category")
    val category: String,
    @Json(name = "char_id")
    val charId: Int,
    @Json(name = "img")
    val img: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "nickname")
    val nickname: String,
    @Json(name = "occupation")
    val occupation: List<String>,
    @Json(name = "portrayed")
    val portrayed: String,
    @Json(name = "status")
    val status: String
)

fun CharacterEntity.mapEntityToDomain() = Character(appearance, betterCallSaulAppearance, birthday, category, charId, img, name, nickname, occupation, portrayed, status)

fun List<CharacterEntity>.mapEntityToDomain(): List<Character> = this.map { it.mapEntityToDomain() }