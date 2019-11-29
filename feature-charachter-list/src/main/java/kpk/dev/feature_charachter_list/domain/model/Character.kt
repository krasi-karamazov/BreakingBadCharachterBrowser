package kpk.dev.feature_charachter_list.domain.model

import com.squareup.moshi.Json

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