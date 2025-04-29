package com.example.resepkita.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.resepkita.data.dummy.DummyData
import com.example.resepkita.ui.components.CategorySection
import com.example.resepkita.ui.components.RecipeCard
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val recipes = remember { DummyData.recipes }
    val pagerState = rememberPagerState()
    var selectedCategory by remember { mutableStateOf("Semua") }

    val filteredRecipes = if (selectedCategory == "Semua") {
        recipes
    } else {
        recipes.filter { it.category == selectedCategory }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ResepKita") },
                actions = {
                    IconButton(onClick = { navController.navigate("search") }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { navController.navigate("favorite") }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                    }
                }
            )
        }
    ) { padding ->
        BoxWithConstraints(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            val maxWidthDp = maxWidth
            val columns = if (maxWidthDp < 600.dp) 1 else 2

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                item {
                    HorizontalPager(
                        count = recipes.take(5).size,
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) { page ->
                        RecipeCard(
                            recipe = recipes[page],
                            onClick = { navController.navigate("detail/${recipes[page].id}") }
                        )
                    }
                    Box(modifier = Modifier.fillMaxWidth()) {
                        HorizontalPagerIndicator(
                            pagerState = pagerState,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(8.dp)
                        )
                    }

                }

                item {
                    CategorySection(
                        categories = listOf("Semua", "Makanan Berat", "Camilan", "Minuman"),
                        selectedCategory = selectedCategory,
                        onCategoryClick = { selectedCategory = it }
                    )
                }

                item {
                    Text(
                        text = if (selectedCategory == "Semua") "Semua Resep" else "Resep ${selectedCategory}",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                items(filteredRecipes.chunked(columns)) { rowRecipes ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        rowRecipes.forEach { recipe ->
                            Box(modifier = Modifier.weight(1f)) {
                                RecipeCard(
                                    recipe = recipe,
                                    onClick = { navController.navigate("detail/${recipe.id}") }
                                )
                            }
                        }
                        if (rowRecipes.size < columns) {
                            repeat(columns - rowRecipes.size) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }
}

