package com.hasangurgur.rickandmortycompose.data.repository

import com.hasangurgur.rickandmortycompose.data.remote.CharacterApi
import com.hasangurgur.rickandmortycompose.data.remote.dto.EpisodeDto
import com.hasangurgur.rickandmortycompose.domain.repository.EpisodeRepository
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val api: CharacterApi
) : EpisodeRepository {
    override suspend fun getEpisodes(page: Int): List<EpisodeDto> {
        return api.getEpisodes(page).results
    }

    override suspend fun getEpisodesByIds(ids: List<Int>): List<EpisodeDto> {
        val idString = ids.joinToString(",") // örn: "1,2,3"
        return api.getEpisodesByIds(idString)
    }

}