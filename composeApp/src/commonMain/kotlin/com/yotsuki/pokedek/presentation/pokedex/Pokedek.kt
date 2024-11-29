package com.yotsuki.pokedek.presentation.pokedex

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.yotsuki.pokedek.networking.ApiResponse
import com.yotsuki.pokedek.networking.client.PokemonClient
import com.yotsuki.pokedek.networking.dto.PokemonResponse
import kotlinx.coroutines.delay

@Composable
fun Pokedex(component: PokedexComponent) {
    val service = PokemonClient()
    var response by remember { mutableStateOf<ApiResponse<PokemonResponse>>(ApiResponse.Loading) }


    LaunchedEffect(Unit) {
        response = ApiResponse.Loading
        delay(1000)
        response = service.getPokemonList(1)
    }
    Box(
        modifier = Modifier.fillMaxSize().padding(horizontal = 25.dp, vertical = 25.dp),
        contentAlignment = Alignment.Center,
    ) {


        when (val state = response) {
            is ApiResponse.Loading -> {
                CircularProgressIndicator()
            }

            is ApiResponse.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    items(state.data.results) { pokemon ->
                        Row(
                            Modifier.animateItem()
                        ) {
                            Card(
                                modifier = Modifier.size(width = 300.dp, height = 200.dp)
                                    .padding(10.dp)
                                    .clickable {
                                        component.navigateToInfo(pokemon.url)
                                    },
                                backgroundColor = Color.White,
                                shape = RoundedCornerShape(10),
                                content = {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        AsyncImage(
                                            modifier = Modifier.padding(
                                                vertical = 10.dp,
                                                horizontal = 10.dp
                                            ),
                                            model = pokemon.imageUrl,
                                            contentDescription = pokemon.name
                                        )
                                        Text(
                                            text = "name:" + pokemon.name
                                        )
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }
                }
            }

            is ApiResponse.Error -> {
                Text(text = "Error: ${state.message}", color = Color.Red)
            }
        }
    }
}