package com.hasangurgur.rickandmortycompose.data.remote

import com.hasangurgur.rickandmortycompose.data.remote.dto.CharacterResponse
import retrofit2.http.GET

interface CharacterApi {

    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}