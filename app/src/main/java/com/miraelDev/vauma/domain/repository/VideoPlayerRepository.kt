package com.miraelDev.vauma.domain.repository

import com.miraelDev.vauma.domain.models.anime.AnimeInfo
import com.miraelDev.vauma.domain.models.anime.PlayerWrapper
import kotlinx.coroutines.flow.StateFlow

interface VideoPlayerRepository {

    fun getVideoPlayer(): StateFlow<PlayerWrapper>

    fun loadVideoId(id :Int)

    fun loadVideoPlayer(animeInfo: AnimeInfo)

    fun loadNextEpisode()

    fun loadPrevEpisode()

    fun releasePlayer()

    fun loadSpecificEpisode(specificEpisode: Int)

}