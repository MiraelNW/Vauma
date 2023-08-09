package com.miraelDev.hikari.presentation.AnimeListScreen.AnimeList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miraelDev.hikari.domain.usecases.homeUseCase.GetAnimeListByCategoryUseCase
import com.miraelDev.hikari.domain.usecases.homeUseCase.GetFilmsAnimeListUseCase
import com.miraelDev.hikari.domain.usecases.homeUseCase.GetNameAnimeListUseCase
import com.miraelDev.hikari.domain.usecases.homeUseCase.GetNewAnimeListUseCase
import com.miraelDev.hikari.domain.usecases.homeUseCase.GetPopularAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(

//    private val getAnimeListByCategoryUseCase: GetAnimeListByCategoryUseCase,

        private val getNewAnimeListUseCase: GetNewAnimeListUseCase,
        private val getFilmsAnimeListUseCase: GetFilmsAnimeListUseCase,
        private val getNameAnimeListUseCase: GetNameAnimeListUseCase,
        private val getPopularAnimeListUseCase: GetPopularAnimeListUseCase

) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d("tag","crash")
    }

    val screenStateNewAnimeList = getNewAnimeListUseCase()
            .filter { it.isNotEmpty() }
            .map { AnimeListScreenState.AnimeList(animes = it) as AnimeListScreenState }
            .onStart { emit(AnimeListScreenState.Loading) }

    val screenStatePopularAnimeList = getPopularAnimeListUseCase()
            .filter { it.isNotEmpty() }
            .map { AnimeListScreenState.AnimeList(animes = it) as AnimeListScreenState }
            .onStart { emit(AnimeListScreenState.Loading) }

    val screenStateNameAnimeList = getNameAnimeListUseCase()
            .filter { it.isNotEmpty() }
            .map { AnimeListScreenState.AnimeList(animes = it) as AnimeListScreenState }
            .onStart { emit(AnimeListScreenState.Loading) }

    val screenStateFilmsAnimeList = getFilmsAnimeListUseCase()
            .filter { it.isNotEmpty() }
            .map { AnimeListScreenState.AnimeList(animes = it) as AnimeListScreenState }
            .onStart { emit(AnimeListScreenState.Loading) }

    fun loadAnimeBtCategory(category: Int) {
        viewModelScope.launch(exceptionHandler) {
//            getAnimeListByCategoryUseCase(category)
        }
    }
}