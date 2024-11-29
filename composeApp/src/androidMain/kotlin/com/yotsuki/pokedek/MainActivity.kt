package com.yotsuki.pokedek

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.retainedComponent
import com.yotsuki.pokedek.presentation.root.RootComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = retainedComponent{
            RootComponent(it)
        }
        setContent {
            App(root)
        }
    }
}

