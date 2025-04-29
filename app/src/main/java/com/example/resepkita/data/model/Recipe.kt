package com.example.resepkita.data.model

data class Recipe(
    val id: Int,
    val title: String,
    val image: Int,
    val ingredients: List<String>,
    val steps: List<String>,
    val duration: String,
    val isFavorite: Boolean = false, // <-- default
    val category: String
)
