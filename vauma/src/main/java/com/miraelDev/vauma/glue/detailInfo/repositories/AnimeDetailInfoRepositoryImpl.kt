package com.miraelDev.vauma.glue.detailInfo.repositories

import com.miraeldev.anime.AnimeDetailInfo
import com.miraeldev.anime.toAnimeInfo
import com.miraeldev.api.AnimeDetailDataRepository
import com.miraeldev.api.Downloader
import com.miraeldev.api.VideoPlayerDataRepository
import com.miraeldev.detailinfo.data.repositories.AnimeDetailRepository
import com.miraeldev.models.result.ResultAnimeDetail
import kotlinx.coroutines.flow.Flow
import me.tatarka.inject.annotations.Inject

@Inject
class AnimeDetailInfoRepositoryImpl(
    private val animeDetailDataRepository: AnimeDetailDataRepository,
    private val videoPlayerDataRepository: VideoPlayerDataRepository,
    private val downloader: Downloader,
) : AnimeDetailRepository {
    override fun getAnimeDetail(): Flow<ResultAnimeDetail> {
        return animeDetailDataRepository.getAnimeDetail()
    }

    override suspend fun loadAnimeDetail(animeId: Int) {
        animeDetailDataRepository.loadAnimeDetail(animeId)
    }

    override suspend fun selectAnimeItem(isSelected: Boolean, animeInfo: AnimeDetailInfo) {
        animeDetailDataRepository.selectAnimeItem(isSelected, animeInfo.toAnimeInfo())
    }

    override suspend fun downloadVideo(url: String, videoName: String) {
        downloader.downloadVideo(url, videoName)
    }

    override fun loadVideoId(animeItem: AnimeDetailInfo, videoId: Int) {
        videoPlayerDataRepository.loadVideoId(animeItem, videoId)
    }
}