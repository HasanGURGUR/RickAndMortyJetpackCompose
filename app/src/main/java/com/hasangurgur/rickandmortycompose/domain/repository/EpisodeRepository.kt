package com.hasangurgur.rickandmortycompose.domain.repository

import com.hasangurgur.rickandmortycompose.data.remote.dto.EpisodeDto

interface EpisodeRepository {
    suspend fun getEpisodes(page : Int = 1): List<EpisodeDto>

    suspend fun getEpisodesByIds(ids: List<Int>): List<EpisodeDto>
}