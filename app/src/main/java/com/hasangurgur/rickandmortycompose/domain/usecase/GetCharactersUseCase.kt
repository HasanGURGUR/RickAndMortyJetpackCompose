package com.hasangurgur.rickandmortycompose.domain.usecase

import com.hasangurgur.rickandmortycompose.data.remote.dto.CharacterDto
import com.hasangurgur.rickandmortycompose.data.remote.dto.CharacterResponse
import com.hasangurgur.rickandmortycompose.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(page: Int = 1) : CharacterResponse{
        return repository.getCharacters(page)
    }
}