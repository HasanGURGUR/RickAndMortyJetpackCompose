package com.hasangurgur.rickandmortycompose.presentation.character_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.hasangurgur.rickandmortycompose.R
import com.hasangurgur.rickandmortycompose.data.remote.dto.CharacterDto

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
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = character.name,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium
                )

                StatusText(status = character.status)
            }
        }
    }
}

@Composable
fun StatusText(status: String) {
    val statusColor = getStatusColor(status)

    Text(
        buildAnnotatedString {
            withStyle(style = MaterialTheme.typography.bodyMedium.toSpanStyle()) { append("Status: ") }
            withStyle(
                style = MaterialTheme.typography.bodyMedium.toSpanStyle().copy(color = statusColor)
            ) {
                append(status)
            }
        }
    )
}


@Composable
fun getStatusColor(status: String): Color {
    return when (status.lowercase()) {
        "alive" -> colorResource(R.color.statu_alive)
        "dead" -> colorResource(R.color.statu_dead)
        "unknown" -> colorResource(R.color.statu_unknown)
        else -> Color.Unspecified
    }
}



