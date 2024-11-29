package com.yotsuki.pokedek

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform