package com.necatdede.mmastatscompose.presentation.screens.home


import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

import com.necatdede.mmastatscompose.presentation.components.FighterCard

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val fighters = viewModel.fighters
    val isLoading = viewModel.isLoading
    val error = viewModel.error

    when {
        isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Hata: ${error}")
            }
        }

        else -> {
            LazyColumn {
                items(fighters.values.toList()) { fighter ->
                    FighterCard(fighter)
                }
            }

        }
    }
}
