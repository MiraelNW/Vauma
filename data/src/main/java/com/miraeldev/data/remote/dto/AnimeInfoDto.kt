package com.miraeldev.data.remote.dto

import com.miraeldev.anime.AnimeDetailInfo
import com.miraeldev.anime.AnimeInfo
import com.miraeldev.data.local.models.filmCategory.FilmCategoryAnimeInfoDbModel
import com.miraeldev.data.local.models.initialSearch.InitialSearchAnimeInfoDbModel
import com.miraeldev.data.local.models.nameCategory.NameCategoryAnimeInfoDbModel
import com.miraeldev.data.local.models.newCategory.NewCategoryAnimeInfoDbModel
import com.miraeldev.data.local.models.popularCategory.PopularCategoryAnimeInfoDbModel
import com.miraeldev.data.local.models.toDbModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AnimeInfoDto(

    @SerialName("id") val id: Int,

    @SerialName("name") val name: String,

    @SerialName("russian") val russianName: String,

    @SerialName("image") val image: ImageModelDto,

    @SerialName("kind") val kind: String,

    @SerialName("score") val score: Float,

    @SerialName("released_on") val releasedOn: String,

    @SerialName("status") val status: String,

    @SerialName("episodes") val episodes: Int,

    @SerialName("rating") val rating: String,

    @SerialName("description") val description: String?,

    @SerialName("duration") val duration: Int,

    @SerialName("genres") val genres: List<GenreDto>,

    @SerialName("similar") val similar: List<SimilarDto>,
)

internal fun AnimeInfoDto.toAnimeDetailInfo(token:String): AnimeDetailInfo {
    return AnimeDetailInfo(
        id = this.id,
        nameRu = this.russianName,
        nameEn = this.name,
        description = this.description ?: "",
        rating = this.rating,
        score = this.score,
        releasedOn = this.releasedOn,
        status = this.status,
        kind = this.kind,
        genres = this.genres.map { it.toGenre() }.toImmutableList(),
        episodes = this.episodes,
        image = this.image.toModel(token),
        duration = this.duration,
        similar = this.similar.map { it.toSimilar(token) }.toPersistentList()
    )
}

internal fun AnimeInfoDto.toAnimeInfo(token:String): AnimeInfo {
    return AnimeInfo(
        id = this.id,
        nameRu = this.russianName,
        nameEn = this.name,
        description = this.description ?: "",
        rating = this.rating,
        score = this.score,
        releasedOn = this.releasedOn,
        status = this.status,
        kind = this.kind,
        genres = this.genres.map { it.toGenre() }.toPersistentList(),
        episodes = this.episodes,
        image = this.image.toModel(token),
        duration = this.duration,
    )
}

internal fun AnimeInfoDto.mapToNewCategoryModel(): NewCategoryAnimeInfoDbModel {
    return NewCategoryAnimeInfoDbModel(
        id = this.id,
        nameRu = this.russianName,
        nameEn = this.name,
        description = this.description ?: "",
        rating = this.rating,
        score = this.score,
        releasedOn = this.releasedOn,
        status = this.status,
        kind = this.kind,
        genres = this.genres.map { it.toGenreDataModel() },
        episodes = this.episodes,
        image = this.image.toDbModel(),
        duration = this.duration,
        page = 0
    )
}

internal fun AnimeInfoDto.mapToPopularCategoryModel(): PopularCategoryAnimeInfoDbModel {
    return PopularCategoryAnimeInfoDbModel(
        id = this.id,
        nameRu = this.russianName,
        nameEn = this.name,
        description = this.description ?: "",
        rating = this.rating,
        score = this.score,
        releasedOn = this.releasedOn,
        status = this.status,
        kind = this.kind,
        genres = this.genres.map { it.toGenreDataModel() },
        episodes = this.episodes,
        image = this.image.toDbModel(),
        duration = this.duration,
        page = 0
    )
}

internal fun AnimeInfoDto.mapToFilmCategoryModel(): FilmCategoryAnimeInfoDbModel {
    return FilmCategoryAnimeInfoDbModel(
        id = this.id,
        nameRu = this.russianName,
        nameEn = this.name,
        description = this.description ?: "",
        rating = this.rating,
        score = this.score,
        releasedOn = this.releasedOn,
        status = this.status,
        kind = this.kind,
        genres = this.genres.map { it.toGenreDataModel() },
        episodes = this.episodes,
        image = this.image.toDbModel(),
        duration = this.duration,
        page = 0
    )
}

internal fun AnimeInfoDto.mapToNameCategoryModel(): NameCategoryAnimeInfoDbModel {
    return NameCategoryAnimeInfoDbModel(
        id = this.id,
        nameRu = this.russianName,
        nameEn = this.name,
        description = this.description ?: "",
        rating = this.rating,
        score = this.score,
        releasedOn = this.releasedOn,
        status = this.status,
        kind = this.kind,
        genres = this.genres.map { it.toGenreDataModel() },
        episodes = this.episodes,
        image = this.image.toDbModel(),
        duration = this.duration,
        page = 0
    )
}

internal fun AnimeInfoDto.mapToInitialSearchModel(): InitialSearchAnimeInfoDbModel {
    return InitialSearchAnimeInfoDbModel(
        id = this.id,
        nameRu = this.russianName,
        nameEn = this.name,
        description = this.description ?: "",
        rating = this.rating,
        score = this.score,
        releasedOn = this.releasedOn,
        status = this.status,
        kind = this.kind,
        genres = this.genres.map { it.toGenreDataModel() },
        episodes = this.episodes,
        image = this.image.toDbModel(),
        duration = this.duration,
        page = 0
    )
}
