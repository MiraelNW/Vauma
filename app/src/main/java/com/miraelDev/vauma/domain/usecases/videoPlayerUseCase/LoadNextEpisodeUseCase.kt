package com.miraelDev.vauma.domain.usecases.videoPlayerUseCase

import com.miraelDev.vauma.domain.repository.VideoPlayerRepository
import javax.inject.Inject

class LoadNextEpisodeUseCase @Inject constructor(val repository: VideoPlayerRepository) {
    operator fun invoke() = repository.loadNextEpisode()
}