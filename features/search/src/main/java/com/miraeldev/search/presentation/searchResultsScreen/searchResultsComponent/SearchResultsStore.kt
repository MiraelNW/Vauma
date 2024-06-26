@file:Suppress("MaxLineLength")
package com.miraeldev.search.presentation.searchResultsScreen.searchResultsComponent

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.miraeldev.models.paging.LoadState
import com.miraeldev.models.paging.PagingState
import com.miraeldev.search.domain.usecases.filterUseCase.GetFilterListUseCase
import com.miraeldev.search.domain.usecases.searchUseCase.GetSearchNameUseCase
import com.miraeldev.search.domain.usecases.searchUseCase.LoadSearchResultsNextPageUseCase
import com.miraeldev.search.domain.usecases.searchUseCase.SaveNameInAnimeSearchHistoryUseCase
import com.miraeldev.search.domain.usecases.searchUseCase.SaveSearchTextUseCase
import com.miraeldev.search.domain.usecases.searchUseCase.SearchAnimeByNameUseCase
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

interface SearchResultsStore :
    Store<SearchResultsStore.Intent, SearchResultsStore.State, SearchResultsStore.Label> {

    sealed interface Intent {
        data class OnAnimeItemClick(val id: Int) : Intent
        data object OnFilterClick : Intent
        data class ShowSearchHistory(val search: String) : Intent
    }

    data class State(
        val canPaginate: Boolean,
        val filterList: ImmutableList<String>,
        val searchResults: PagingState
    )

    sealed interface Label {
        data class OnAnimeItemClick(val id: Int) : Label
        data object OnFilterClick : Label
        data object OnShowSearchHistory : Label
    }
}

@Inject
class SearchResultsStoreFactory(
    private val storeFactory: StoreFactory,
    private val searchAnimeByName: SearchAnimeByNameUseCase,
    private val saveNameInAnimeSearchHistory: SaveNameInAnimeSearchHistoryUseCase,
    private val getSearchName: GetSearchNameUseCase,
    private val loadSearchResultsNextPageUseCase: LoadSearchResultsNextPageUseCase,
    private val saveSearchTextUseCase: SaveSearchTextUseCase,
    private val getFilterListUseCase: GetFilterListUseCase
) {

    fun create(): SearchResultsStore =
        object :
            SearchResultsStore,
            Store<SearchResultsStore.Intent, SearchResultsStore.State, SearchResultsStore.Label> by storeFactory.create(
                name = "SearchResultsStore",
                initialState = SearchResultsStore.State(
                    false,
                    persistentListOf(),
                    PagingState(emptyList(), LoadState.INITIAL)
                ),
                bootstrapper = BootstrapperImpl(),
                executorFactory = ::ExecutorImpl,
                reducer = ReducerImpl
            ) {}

    private sealed interface Action {
        data class SearchNameLoaded(val name: String, val result: Flow<PagingState>) :
            Action

        data class SearchFiltersLoaded(val filters: List<String>) : Action
        data class SearchResultsLoaded(val results: PagingState) : Action
    }

    private sealed interface Msg {
        data class SearchNameLoaded(val name: String, val result: PagingState) : Msg
        data class SearchFiltersLoaded(val filters: List<String>) : Msg
        data class SearchResultsLoaded(val results: PagingState) : Msg
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                getSearchName()
                    .collect {
                        val result = searchAnimeByName(it)
                        loadSearchResultsNextPageUseCase()
                        dispatch(Action.SearchNameLoaded(it, result))
                        saveNameInAnimeSearchHistory(it)
                    }
            }
            scope.launch {
                getFilterListUseCase().collect {
                    dispatch(Action.SearchFiltersLoaded(it))
                }
            }
        }
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<SearchResultsStore.Intent, Action, SearchResultsStore.State, Msg, SearchResultsStore.Label>() {
        override fun executeIntent(
            intent: SearchResultsStore.Intent,
            getState: () -> SearchResultsStore.State
        ) {
            when (intent) {
                is SearchResultsStore.Intent.OnAnimeItemClick -> publish(
                    SearchResultsStore.Label.OnAnimeItemClick(intent.id)
                )

                is SearchResultsStore.Intent.OnFilterClick -> publish(SearchResultsStore.Label.OnFilterClick)
                is SearchResultsStore.Intent.ShowSearchHistory -> {
                    saveSearchTextUseCase(intent.search)
                    publish(SearchResultsStore.Label.OnShowSearchHistory)
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> SearchResultsStore.State) {
            when (action) {
                is Action.SearchNameLoaded -> {
                    scope.launch {
                        action.result.collect {
                            dispatch(
                                Msg.SearchNameLoaded(
                                    action.name,
                                    it
                                )
                            )
                        }
                    }
                }

                is Action.SearchFiltersLoaded -> dispatch(Msg.SearchFiltersLoaded(action.filters))
                is Action.SearchResultsLoaded -> dispatch(Msg.SearchResultsLoaded(action.results))
            }
        }
    }

    private object ReducerImpl : Reducer<SearchResultsStore.State, Msg> {
        override fun SearchResultsStore.State.reduce(msg: Msg): SearchResultsStore.State =
            when (msg) {
                is Msg.SearchNameLoaded -> copy(searchResults = msg.result)

                is Msg.SearchFiltersLoaded -> {
                    copy(filterList = msg.filters.toPersistentList())
                }

                is Msg.SearchResultsLoaded -> {
                    copy(
                        searchResults = msg.results
                    )
                }
            }
    }
}
