package com.hasangurgur.rickandmortycompose.domain.repository

import com.hasangurgur.rickandmortycompose.data.remote.dto.CharacterDto

interface CharacterRepository {
    suspend fun getCharacters(): List<CharacterDto>
}