package com.miraelDev.vauma.presentation.favouriteListScreen

import com.miraelDev.vauma.data.remote.FailureCauses
import com.miraelDev.vauma.domain.models.AnimeInfo

sealed class FavouriteListScreenState {

    object Loading : FavouriteListScreenState()

    object Initial : FavouriteListScreenState()

    data class Failure(val failure: FailureCauses) : FavouriteListScreenState()

    data class Result(val result: List<AnimeInfo>) : FavouriteListScreenState()

}