package com.hasangurgur.rickandmortycompose.presentation.character_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.hasangurgur.rickandmortycompose.data.remote.dto.CharacterDto
import com.hasangurgur.rickandmortycompose.presentation.character_list.CharacterUiState
import com.hasangurgur.rickandmortycompose.presentation.character_list.CharacterViewModel

@Composable
fun CharacterDetailScreen(
    characterId: Int,
    viewModel: CharacterViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val character = when (uiState) {
        is CharacterUiState.Success -> uiState.characters.find { it.id == characterId }
        else -> null
    }
    CharacterDetailContent(character = character)
}


@Composable
fun CharacterDetailContent(character: CharacterDto?) {
    character?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = character.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.FillBounds
            )
            Text(text = it.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = "Status: ${it.status}", style = MaterialTheme.typography.bodyLarge)
            // Other details...
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Karakter bulunamadı.")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CharacterDetailContentPreview() {
    val fakeCharacter = CharacterDto(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        species = "Human",
    )
    CharacterDetailContent(character = fakeCharacter)
}
