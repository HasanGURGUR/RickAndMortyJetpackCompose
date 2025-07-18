package com.hasangurgur.rickandmortycompose.data.repository

import com.hasangurgur.rickandmortycompose.data.remote.CharacterApi
import com.hasangurgur.rickandmortycompose.data.remote.dto.CharacterResponse
import com.hasangurgur.rickandmortycompose.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api : CharacterApi
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): CharacterResponse {
        return api.getCharacters(page)
    }
}