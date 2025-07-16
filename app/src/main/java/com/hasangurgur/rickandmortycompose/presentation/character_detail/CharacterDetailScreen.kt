package com.hasangurgur.rickandmortycompose.presentation.character_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.hasangurgur.rickandmortycompose.R
import com.hasangurgur.rickandmortycompose.data.remote.dto.CharacterDto
import com.hasangurgur.rickandmortycompose.data.remote.dto.LocationOriginDto
import com.hasangurgur.rickandmortycompose.presentation.character_list.CharacterUiState
import com.hasangurgur.rickandmortycompose.presentation.character_list.CharacterViewModel

@Composable
fun CharacterDetailScreen(
    characterId: Int, viewModel: CharacterViewModel = hiltViewModel()
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
                .statusBarsPadding()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
            Text(
                text = it.name,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Status: ${it.status}",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .height(IntrinsicSize.Min)
                    .background(
                        color = colorResource(R.color.gray_container),

                        )
            ) {
                InfoRowItem(title = "Species", characterValue = character.species)
                InfoRowItem(title = "Gender", characterValue = character.gender)
                InfoRowItem(title = "Status", characterValue = character.status)
                InfoRowItem(title = "Location", characterValue = character.location.name)
                InfoRowItem(title = "Origin", characterValue = character.origin.name)
            }
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Karakter bulunamadı.")
        }
    }
}

@Composable
fun InfoRowItem(title: String, characterValue: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${title}: ",
            style = MaterialTheme.typography.bodyLarge,
            color = colorResource(R.color.black)
        )

        Text(
            text = characterValue,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.End,
            color = colorResource(R.color.black)
        )
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
        gender = "Male",
        location = LocationOriginDto(name = "Earth", url = ""),
        origin = LocationOriginDto(name = "Earth (C-137)", url = "")
    )
    CharacterDetailContent(character = fakeCharacter)
}
