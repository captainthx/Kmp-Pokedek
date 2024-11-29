package com.yotsuki.pokedek.networking.dto

import com.yotsuki.pokedek.networking.model.Pokemon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: String?,
    @SerialName("results") val results: List<Pokemon>
)