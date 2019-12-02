package kpk.dev.feature_character_list

import kpk.dev.feature_character_list.datasource.model.CharacterEntity
import kpk.dev.feature_character_list.domain.model.Character

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
val seasonAppearances = listOf(3, 4, 5)
val betterCallSaulAppearances = listOf(1, 2, 3)
val occupation = listOf("Kid")
val characterEntity = CharacterEntity(seasonAppearances, betterCallSaulAppearances, "Unknown", "Breaking Bad", 24, "https://vignette.wikia.nocookie.net/breakingbad/images/9/97/Brock.png/revision/latest?cb=20170725193144", "Brock Cantillo", "Brock", occupation, "Ian Posada", "Alive")

val character = Character(seasonAppearances, betterCallSaulAppearances, "Unknown", "Breaking Bad", 24, "https://vignette.wikia.nocookie.net/breakingbad/images/9/97/Brock.png/revision/latest?cb=20170725193144", "Brock Cantillo", "Brock", occupation, "Ian Posada", "Alive")
