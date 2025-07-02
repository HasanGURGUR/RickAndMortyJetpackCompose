package com.hasangurgur.rickandmortycompose.presentation.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasangurgur.rickandmortycompose.data.remote.dto.CharacterDto
import com.hasangurgur.rickandmortycompose.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface CharacterUiState {
    object Loading : CharacterUiState
    data class Success(val characters : List<CharacterDto>) : CharacterUiState
    data class Error(val message : String) : CharacterUiState
}

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow<CharacterUiState>(CharacterUiState.Loading)
    val uiState : StateFlow<CharacterUiState> = _uiState

    init {
        fetchCharacters()
    }

    private fun fetchCharacters(){
        viewModelScope.launch {
            try {
                val characters = getCharactersUseCase()
                _uiState.value = CharacterUiState.Success(characters)
            }catch (e: Exception) {
                _uiState.value = CharacterUiState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }
}