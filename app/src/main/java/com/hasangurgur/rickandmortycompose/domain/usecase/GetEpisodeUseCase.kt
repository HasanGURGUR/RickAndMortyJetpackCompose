package com.hasangurgur.rickandmortycompose.domain.usecase

import com.hasangurgur.rickandmortycompose.data.remote.dto.EpisodeDto
import com.hasangurgur.rickandmortycompose.domain.repository.EpisodeRepository
import javax.inject.Inject

class GetEpisodeUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    suspend operator fun invoke(page : Int = 1) : List<EpisodeDto>{
        return repository.getEpisodes(page)
    }
}