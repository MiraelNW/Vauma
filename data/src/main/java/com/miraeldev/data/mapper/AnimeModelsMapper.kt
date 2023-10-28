package com.miraeldev.data.mapper

import com.miraeldev.anime.AnimeInfo
import com.miraeldev.anime.Genre
import com.miraeldev.anime.VideoInfo
import com.miraeldev.data.local.models.favourite.AnimeInfoDbModel
import com.miraeldev.domain.models.animeDataModels.GenreDataModel
import com.miraeldev.domain.models.animeDataModels.VideoInfoDataModel
import javax.inject.Inject

internal class AnimeModelsMapper @Inject constructor() {


    fun mapAnimeInfoToAnimeDbModel(animeInfo: AnimeInfo): AnimeInfoDbModel {
        return AnimeInfoDbModel(
            id = animeInfo.id,
            nameEn = animeInfo.nameEn,
            nameRu = animeInfo.nameRu,
            image = animeInfo.image,
            kind = animeInfo.kind,
            score = animeInfo.score,
            status = animeInfo.status,
            rating = animeInfo.rating,
            airedOn = animeInfo.airedOn,
            episodes = animeInfo.episodes,
            duration = animeInfo.duration,
            description = animeInfo.description,
            videoUrls = mapVideoInfoToDataModel(animeInfo.videoUrls),
            genres = animeInfo.genres.map { mapGenreToDataModel(it) },
            page = 0
        )
    }

    private fun mapVideoInfoToDataModel(videoInfo: VideoInfo): VideoInfoDataModel {
        return VideoInfoDataModel(
            id = videoInfo.id,
            imageUrl = videoInfo.imageUrl,
            playerUrl = videoInfo.playerUrl,
            videoName = videoInfo.videoName,
        )
    }


    fun mapGenreToDataModel(genre: Genre): GenreDataModel {
        return GenreDataModel(
            id = genre.id,
            nameRu = genre.nameRu,
            nameEn = genre.nameEn,
        )
    }
}