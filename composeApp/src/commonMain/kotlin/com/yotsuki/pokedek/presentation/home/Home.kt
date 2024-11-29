package com.yotsuki.pokedek.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yotsuki.pokedek.components.HomeCard
import pokedek.composeapp.generated.resources.Res
import pokedek.composeapp.generated.resources.electric_boltx
import pokedek.composeapp.generated.resources.genetics
import pokedek.composeapp.generated.resources.location_on
import pokedek.composeapp.generated.resources.sports_and_outdoors

@Composable
fun Home(component: HomeComponent) {

    Box(
        modifier =
        Modifier.fillMaxSize()
            .padding(25.dp)
            .clickable { },
        contentAlignment = Alignment.Center
    ) {

        // Cards Section
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                HomeCard(
                    backgroundColor = Color(0xFFEA5656),
                    icon = Res.drawable.sports_and_outdoors, // ใช้ไอคอน Pokedex
                    text = "Pokedex"
                ) { component.onClickToPokeList() }
                HomeCard(
                    backgroundColor = Color(0xFFFFA726),
                    icon = Res.drawable.electric_boltx, // ใช้ไอคอน Moves
                    text = "Moves"
                ) { }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                HomeCard(
                    backgroundColor = Color(0xFF66BB6A),
                    icon = Res.drawable.genetics, // ใช้ไอคอน Evolutions
                    text = "Evolutions"
                ) { }
                HomeCard(
                    backgroundColor = Color(0xFF42A5F5),
                    icon = Res.drawable.location_on, // ใช้ไอคอน Locations
                    text = "Locations"
                ) { }
            }
        }
    }
}