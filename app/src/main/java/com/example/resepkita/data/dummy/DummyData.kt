package com.example.resepkita.data.dummy
import androidx.compose.runtime.mutableStateListOf
import com.example.resepkita.R
import com.example.resepkita.data.model.Recipe

object DummyData {
    val recipes = mutableStateListOf(
        Recipe(
            id = 1,
            title = "Nasi Goreng",
            image = R.drawable.nasi_goreng, // gambar kamu taruh di drawable
            ingredients = listOf("Nasi", "Bawang", "Kecap", "Telur"),
            steps = listOf("Panaskan minyak", "Tumis bawang", "Masukkan nasi", "Tambahkan kecap dan telur", "Aduk rata"),
            duration = "30 menit",
            isFavorite = false,
            category = "Makanan Berat"
        ),
        Recipe(
            id = 2,
            title = "Sate Ayam",
            image = R.drawable.sate_ayam,
            ingredients = listOf("Ayam", "Tusuk sate", "Kacang", "Kecap"),
            steps = listOf("Potong ayam", "Tusuk dengan tusukan sate", "Bakar sate", "Olesi saus kacang"),
            duration = "40 menit",
            isFavorite = false,
            category = "Makanan Berat"
        ),
        Recipe(
            id = 3,
            title = "Es Teh Manis",
            image = R.drawable.es_teh_manis,
            ingredients = listOf("Teh", "Gula", "Es Batu"),
            steps = listOf("Seduh teh", "Tambahkan gula", "Masukkan es batu"),
            duration = "10 menit",
            isFavorite = false,
            category = "Minuman"
        ),
        Recipe(
            id = 4,
            title = "Kentang Goreng",
            image = R.drawable.kentang_goreng,
            ingredients = listOf("Kentang", "Minyak Goreng", "Garam"),
            steps = listOf("Kupas kentang", "Iris kentang", "Goreng kentang hingga matang", "Tiriskan dan beri garam"),
            duration = "15 menit",
            isFavorite = false,
            category = "Camilan"
        )
    )
    fun toggleFavorite(recipeId: Int) {
        val index = recipes.indexOfFirst { it.id == recipeId }
        if (index != -1) {
            val recipe = recipes[index]
            val updatedRecipe = recipe.copy(isFavorite = !recipe.isFavorite)
            recipes.removeAt(index)
            recipes.add(index, updatedRecipe)
        }
    }

}
