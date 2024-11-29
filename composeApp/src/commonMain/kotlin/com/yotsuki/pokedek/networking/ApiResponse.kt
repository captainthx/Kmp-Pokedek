package com.yotsuki.pokedek.networking

sealed class ApiResponse<out T> {
    data object Loading : ApiResponse<Nothing>()
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String) : ApiResponse<Nothing>()
}