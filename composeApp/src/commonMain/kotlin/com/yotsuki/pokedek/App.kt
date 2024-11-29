package com.yotsuki.pokedek

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.yotsuki.pokedek.presentation.home.Home
import com.yotsuki.pokedek.presentation.info.Info
import com.yotsuki.pokedek.presentation.pokedex.Pokedex
import com.yotsuki.pokedek.presentation.root.RootComponent
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(root: RootComponent) {
    val childStack by root.childStack.subscribeAsState()

    MaterialTheme {
        Scaffold(
            backgroundColor = Color(0xFFECF0F1),
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.surface,
                    title = {
                        Text("Top app bar")
                    }
                )
                Spacer(modifier = Modifier.height(15.dp).padding(20.dp))
            },
        ) {
            Children(
                stack = childStack,
                animation = stackAnimation(slide())
            ) { child ->
                when (val instance = child.instance) {
                    is RootComponent.Child.HomeScreen -> Home(instance.component)
                    is RootComponent.Child.Info -> Info(
                        instance.component.text,
                        instance.component,
                    )

                    is RootComponent.Child.Pokedexs -> Pokedex(instance.component)
                }
            }
        }
    }
}