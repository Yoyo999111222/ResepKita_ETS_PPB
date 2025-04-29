package com.example.resepkita.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.resepkita.ui.home.HomeScreen
import com.example.resepkita.ui.screen.detail.DetailScreen
import com.example.resepkita.ui.screen.search.SearchScreen
import com.example.resepkita.ui.screen.favorite.FavoriteScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("detail/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")?.toInt() ?: 0
            DetailScreen(recipeId, navController)
        }
        composable("search") { SearchScreen(navController) }
        composable("favorite") { FavoriteScreen(navController) }
    }
}
