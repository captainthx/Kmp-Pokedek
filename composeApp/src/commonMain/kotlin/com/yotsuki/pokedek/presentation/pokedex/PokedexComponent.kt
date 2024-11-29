package com.yotsuki.pokedek.presentation.pokedex

import com.arkivanov.decompose.ComponentContext

class PokedexComponent(
    component: ComponentContext,
    private val onNavigateToInfo: (String) -> Unit
) : ComponentContext by component {

    fun navigateToInfo(text: String) {
        onNavigateToInfo(text)
    }

}