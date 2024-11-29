package com.yotsuki.pokedek.presentation.info

import com.arkivanov.decompose.ComponentContext

class InfoComponent(
    val text: String,
    component: ComponentContext,
    private val onGoBack: () -> Unit
) : ComponentContext by component {

    fun goBack() {
        onGoBack()
    }
}