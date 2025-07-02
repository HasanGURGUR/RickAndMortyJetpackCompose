package com.hasangurgur.rickandmortycompose.presentation.character_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.hasangurgur.rickandmortycompose.data.remote.dto.CharacterDto
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun CharacterScreen(viewModel: CharacterViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        is CharacterUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is CharacterUiState.Success -> {
            CharacterList(characters = uiState.characters)
        }
        is CharacterUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Hata: ${uiState.message}")
            }
        }
    }
}

@Composable
fun CharacterList(characters: List<CharacterDto>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(characters) { character ->
            CharacterItem(character)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CharacterItem(character: CharacterDto) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = character.name,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = character.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Status: ${character.status}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
