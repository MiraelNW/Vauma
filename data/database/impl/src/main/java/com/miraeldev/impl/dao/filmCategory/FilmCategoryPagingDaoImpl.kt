package com.miraeldev.impl.dao.filmCategory

import com.miraeldev.Database
import com.miraeldev.api.filmCategory.FilmCategoryPagingDao
import com.miraeldev.extensions.toLong
import com.miraeldev.impl.mapper.mapToFilmCategoryDbModel
import com.miraeldev.impl.mapper.toLastDbNode
import com.miraeldev.impl.mapper.toPagingAnimeInfo
import com.miraeldev.models.paging.LastDbNode
import com.miraeldev.models.paging.PagingAnimeInfo
import me.tatarka.inject.annotations.Inject

@Inject
class FilmCategoryPagingDaoImpl(private val database: Database) : FilmCategoryPagingDao {
    private val query = database.film_category_paging_tableQueries
    override suspend fun insertAll(
        anime: List<PagingAnimeInfo>,
        page: Long,
        isLast: Boolean,
        insertTime: Long
    ) {
        val insertList = anime.map { it.mapToFilmCategoryDbModel() }
        query.transaction {
            insertList.forEach {
                query.insert(
                    id = it.id,
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
                    isFavourite = it.isFavourite,
                    page = page,
                    isLast = isLast.toLong(),
                    createTime = insertTime
                )
            }
        }
    }

    override fun getLastNode(): LastDbNode {
        return query.getLastNode().executeAsOneOrNull().toLastDbNode()
    }

    override fun getAnimeByPage(page: Long): List<PagingAnimeInfo> {
        return query.getPagingAnime(page = page).executeAsList().map { it.toPagingAnimeInfo() }
    }

    override suspend fun clearAllAnime() {
        query.clearAllAnime()
    }
}