package com.hasangurgur.rickandmortycompose.presentation.character_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasangurgur.rickandmortycompose.data.remote.dto.CharacterDto
import com.hasangurgur.rickandmortycompose.data.remote.dto.EpisodeDto
import com.hasangurgur.rickandmortycompose.domain.usecase.GetEpisodesByIdsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getEpisodeUseCase: GetEpisodesByIdsUseCase
) : ViewModel() {
    private val _episodes = mutableStateOf<List<EpisodeDto>>(emptyList())
    val episodes: State<List<EpisodeDto>> = _episodes

    fun loadEpisodes(character: CharacterDto) {
        val ids = character.episode.mapNotNull {
            it.substringAfterLast("/").toIntOrNull()
        }

        viewModelScope.launch {
            try {
                _episodes.value = getEpisodeUseCase(ids)
            } catch (e: Exception) {

            }
        }
    }
}