package com.miraeldev.local.dao.nameCategory.impl

import com.miraeldev.Database
import com.miraeldev.anime.AnimeInfo
import com.miraeldev.local.dao.nameCategory.api.NameCategoryDao
import com.miraeldev.local.mapper.mapToFilmCategoryModel
import com.miraeldev.local.mapper.mapToNameCategoryModel
import com.miraeldev.local.mapper.toAnimeInfo
import com.miraeldev.models.dto.AnimeInfoDto
import me.tatarka.inject.annotations.Inject

@Inject
class NameCategoryDaoImpl(private val database: Database) : NameCategoryDao {

    private val query = database.name_category_tableQueries
    override suspend fun insertAll(anime: List<AnimeInfoDto>) {
        val insertList = anime.map { it.mapToNameCategoryModel() }
        query.transaction {
            insertList.forEach {
                query.insert(
                    id =  it.id,
                    nameEn = it.nameEn,
                    nameRu = it.nameRu,
                    image = it.image,
                    kind = it.kind,
                    score = it.score,
                    status = it.status,
                    rating = it.rating,
                    releasedOn = it.releasedOn,
                    episodes = it.episodes,
                    duration = it.duration,
                    description = it.description,
                    videoUrls = it.videoUrls,
                    genres = it.genres,
                    isFavourite = it.isFavourite
                )
            }
        }
    }

    override suspend fun getAnimeList(): List<AnimeInfo> {
        return query.getAnimeList().executeAsList().map { it.toAnimeInfo() }
    }

    override suspend fun isEmpty(): Boolean {
        return query.isEmpty().executeAsOne()
    }
}