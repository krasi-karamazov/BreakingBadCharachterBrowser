package kpk.dev.feature_character_list.domain.model

data class Character(
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
    val status: String
)