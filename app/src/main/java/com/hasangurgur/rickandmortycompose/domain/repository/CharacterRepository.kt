package com.hasangurgur.rickandmortycompose.domain.repository

import com.hasangurgur.rickandmortycompose.data.remote.dto.CharacterResponse

interface CharacterRepository {
    suspend fun getCharacters(page: Int = 1): CharacterResponse
}