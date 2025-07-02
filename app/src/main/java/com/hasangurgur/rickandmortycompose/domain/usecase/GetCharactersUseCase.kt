package com.hasangurgur.rickandmortycompose.domain.usecase

import com.hasangurgur.rickandmortycompose.data.remote.dto.CharacterDto
import com.hasangurgur.rickandmortycompose.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke() : List<CharacterDto>{
        return repository.getCharacters()
    }
}