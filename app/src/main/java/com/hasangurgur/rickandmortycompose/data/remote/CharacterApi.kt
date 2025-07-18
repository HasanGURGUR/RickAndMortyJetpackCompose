package com.hasangurgur.rickandmortycompose.data.remote

import com.hasangurgur.rickandmortycompose.data.remote.dto.CharacterResponse
import com.hasangurgur.rickandmortycompose.data.remote.dto.EpisodeDto
import com.hasangurgur.rickandmortycompose.data.remote.dto.EpisodeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int = 1): CharacterResponse

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page : Int = 1): EpisodeResponse

    @GET("episode/{ids}")
    suspend fun getEpisodesByIds(@Path("ids") ids: String): List<EpisodeDto>
}