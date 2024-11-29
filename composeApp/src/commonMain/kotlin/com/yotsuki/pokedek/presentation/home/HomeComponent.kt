package com.yotsuki.pokedek.presentation.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class HomeComponent(
    component: ComponentContext,
    private val onNavigateToPokedex: () -> Unit
) : ComponentContext by component {

    fun onClickToPokeList() {
        onNavigateToPokedex()
    }


}