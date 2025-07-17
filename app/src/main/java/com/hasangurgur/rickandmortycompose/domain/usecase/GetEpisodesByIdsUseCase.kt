package com.hasangurgur.rickandmortycompose.domain.usecase

import com.hasangurgur.rickandmortycompose.data.remote.dto.EpisodeDto
import com.hasangurgur.rickandmortycompose.domain.repository.EpisodeRepository
import javax.inject.Inject

class GetEpisodesByIdsUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    suspend operator fun invoke(ids: List<Int>): List<EpisodeDto> {
        return repository.getEpisodesByIds(ids)
    }
}
