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

    private var currentPage = 1
    private var isLoading = false
    private var endReached = false
    private val loadedCharacters = mutableListOf<CharacterDto>()
    private var totalPages: Int? = null
    private var isPagingLoading = false

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        if (isLoading || endReached) return
        if (currentPage == 1) {
            isLoading = true
            _uiState.value = CharacterUiState.Loading
        } else {
            isPagingLoading = true
            // UI'da bu değişkeni kullanacağız
        }

        viewModelScope.launch {
            try {
                val response = getCharactersUseCase(currentPage)
                totalPages = response.info.pages
                if (currentPage > (totalPages ?: 1)) {
                    endReached = true
                } else {
                    loadedCharacters.addAll(response.results)
                    _uiState.value = CharacterUiState.Success(loadedCharacters.toList())
                    currentPage++
                    if (currentPage > (totalPages ?: 1)) {
                        endReached = true
                    }
                }
            } catch (e: Exception) {
                _uiState.value = CharacterUiState.Error(e.message ?: "An unexpected error occurred")
            } finally {
                isLoading = false
                isPagingLoading = false
            }
        }
    }

    fun isPaging(): Boolean = isPagingLoading
}