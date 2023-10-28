package com.miraeldev.data.local.models.nameCategory

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.miraeldev.domain.models.animeDataModels.GenreDataModel
import com.miraeldev.domain.models.animeDataModels.VideoInfoDataModel
import kotlinx.serialization.Serializable


@Entity(tableName = "name_category_anime")
@Serializable
internal data class NameCategoryAnimeInfoDbModel(

    @PrimaryKey val id: Int,
    val nameEn: String,
    val nameRu: String,
    val image: String,
    val kind: String,
    val score: Float,
    val status: String,
    val rating: String,
    val airedOn: String,
    val episodes: Int,
    val duration: Int,
    val description: String,
    val videoUrls: VideoInfoDataModel = VideoInfoDataModel(),
    val genres: List<GenreDataModel>,
    var page: Int
)