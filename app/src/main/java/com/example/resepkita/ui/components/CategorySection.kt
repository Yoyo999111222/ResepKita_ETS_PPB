package com.example.resepkita.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategorySection(
    categories: List<String>,
    selectedCategory: String,
    onCategoryClick: (String) -> Unit // Menambahkan parameter untuk menangani klik kategori
) {
    LazyRow(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        items(categories) { category ->
            // Menggunakan Chip dari Material3 untuk kategori
            FilterChip(
                selected = selectedCategory == category, // Menentukan apakah chip terpilih atau tidak
                onClick = { onCategoryClick(category) }, // Memanggil onCategoryClick saat kategori dipilih
                label = { Text(category) },
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}
