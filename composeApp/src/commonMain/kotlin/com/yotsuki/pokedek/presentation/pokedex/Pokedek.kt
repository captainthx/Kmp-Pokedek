package com.yotsuki.pokedek.presentation.pokedex

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.yotsuki.pokedek.networking.ApiResponse
import com.yotsuki.pokedek.networking.client.PokemonClient
import com.yotsuki.pokedek.networking.model.Pokemon
import kotlinx.coroutines.delay

@Composable
fun Pokedex(component: PokedexComponent) {
    val service = PokemonClient()
    val pokemonList = remember { mutableStateListOf<Pokemon>() }
    var isLoading by remember { mutableStateOf(false) }
    var offset by remember { mutableStateOf(0L) }
    val limit = 10L
    val lazyListState = rememberLazyGridState()

    // โหลดข้อมูลเริ่มต้น
    LaunchedEffect(Unit) {
        isLoading = true
        val response = service.getPokemonList(limit, offset)
        if (response is ApiResponse.Success) {
            pokemonList.addAll(response.data.results)
        }
        isLoading = false
    }

    // โหลดข้อมูลเมื่อเลื่อนถึงล่างสุด
    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val totalItems = lazyListState.layoutInfo.totalItemsCount
                val lastVisibleItem = visibleItems.lastOrNull()?.index ?: 0

                if (!isLoading && totalItems > 0 && lastVisibleItem == totalItems - 1) {
                    // โหลดหน้าใหม่เมื่อเลื่อนถึงล่างสุด
                    isLoading = true
                    offset += limit
                    val response = service.getPokemonList(limit, offset)
                    if (response is ApiResponse.Success) {
                        pokemonList.addAll(response.data.results)
                    }
                    isLoading = false
                }
            }
    }

    LazyVerticalGrid(
        state = lazyListState,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.fillMaxSize().padding(horizontal = 25.dp, vertical = 25.dp)
    ) {
        items(pokemonList) { pokemon ->
            Row(
                Modifier.animateItem()
            ) {
                Card(
                    modifier = Modifier.size(width = 300.dp, height = 250.dp)
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
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                text = pokemon.name
                            )
                            Text(
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                                text = pokemon.numberString
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
        }

        // เพิ่ม Loading Item ด้านล่าง
        if (isLoading) {
            item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
    }
}
