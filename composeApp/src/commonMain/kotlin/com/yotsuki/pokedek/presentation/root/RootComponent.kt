package com.yotsuki.pokedek.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.yotsuki.pokedek.presentation.info.InfoComponent
import com.yotsuki.pokedek.presentation.home.HomeComponent
import com.yotsuki.pokedek.presentation.pokedex.PokedexComponent
import kotlinx.serialization.Serializable

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    val childStack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.HomeScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: Config,
        context: ComponentContext
    ): Child {
        return when (config) {
            is Config.HomeScreen -> Child.HomeScreen(
                HomeComponent(context,
                    onNavigateToPokedex = {
                        navigation.pushNew(Config.Pokedex)
                    }

                ))

            is Config.Info -> Child.Info(
                InfoComponent(config.text, context,
                    onGoBack = {
                        navigation.pop()
                    }
                )
            )

            is Config.Pokedex -> Child.Pokedexs(
                PokedexComponent(context,
                    onNavigateToInfo = { text ->
                        navigation.pushNew(Config.Info(text))
                    }
                )
            )
        }
    }

    sealed class Child {
        data class HomeScreen(val component: HomeComponent) : Child()
        data class Info(val component: InfoComponent) : Child()
        data class Pokedexs(val component: PokedexComponent) : Child()
    }

    @Serializable
    sealed class Config {
        @Serializable
        data object HomeScreen : Config()

        @Serializable
        data class Info(val text: String) : Config()

        @Serializable
        data object Pokedex : Config()
    }
}