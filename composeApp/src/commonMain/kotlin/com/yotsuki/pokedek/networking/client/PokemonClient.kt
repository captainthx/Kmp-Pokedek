package com.yotsuki.pokedek.networking.client

import com.yotsuki.pokedek.networking.ApiResponse
import com.yotsuki.pokedek.networking.HttpNetwork.httpClient
import com.yotsuki.pokedek.networking.dto.PokemonResponse
import io.ktor.client.call.body
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path

class PokemonClient() {

    suspend fun getPokemonList(limit: Long, offset: Long): ApiResponse<PokemonResponse> {
        return try {
            val response = httpClient.get {
                url {
                    path("pokemon")
                    parameters.append("limit", limit.toString())
                    parameters.append("offset", offset.toString())
                }
                contentType(ContentType.Application.Json)
            }
            ApiResponse.Success(response.body())
        } catch (e: RedirectResponseException) {
            println("GetPokemonList-[error]. error: ${e.response.status.description}")
            ApiResponse.Error(e.response.status.description)
        }
    }

}