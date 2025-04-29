package com.example.resepkita.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.resepkita.data.dummy.DummyData
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(recipeId: Int, navController: NavController) {
    val recipe = DummyData.recipes.find { it.id == recipeId } ?: return
    val recipeIndex = DummyData.recipes.indexOf(recipe)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(recipe.title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            DummyData.recipes.set(recipeIndex, recipe.copy(isFavorite = !recipe.isFavorite))

                        }
                    ) {
                        Icon(
                            imageVector = if (recipe.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorite"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = recipe.image),
                contentDescription = recipe.title,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Bahan-bahan:", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
            recipe.ingredients.forEach {
                Text("- $it")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Langkah-langkah:", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
            recipe.steps.forEachIndexed { index, step ->
                Text("${index + 1}. $step")
            }
        }
    }
}

