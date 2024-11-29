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

    suspend fun getPokemonList(page: Long): ApiResponse<PokemonResponse> {
        try {
            var response = httpClient.get {
                url {
                    path("pokemon")
                    parameters.append("limit", PageSize.toString())
                    parameters.append("offset", (page * PageSize).toString())
                }
                contentType(ContentType.Application.Json)
            }
            return ApiResponse.Success(response.body())
        } catch (e: RedirectResponseException) {
            println("GetPokemonList-[error]. error: ${e.response.status.description}")
            return ApiResponse.Error(e.response.status.description)
        }
    }

    companion object {
        private const val PageSize = 20
    }
}