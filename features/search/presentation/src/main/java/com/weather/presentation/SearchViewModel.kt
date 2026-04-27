package com.weather.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.datastore.usecases.SaveCityUseCase
import com.weather.domain.model.SearchRequestModel
import com.weather.domain.model.CityModel
import com.weather.domain.usecase.SearchCityUseCase
import com.weather.presentation.state.SearchAction
import com.weather.presentation.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val saveCityUseCase: SaveCityUseCase,
    private val searchCityUseCase: SearchCityUseCase
) : ViewModel() {

    private var cities: List<CityModel> = emptyList()

    private val _state = MutableStateFlow(SearchState())
    val state = _state
        .onStart {
            observeSearchQuery()
        }.stateIn(
            viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            _state.value
        )


    fun onAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(
                        searchQuery = action.query
                    )
                }
            }
            is SearchAction.OnCitySelected -> {
                saveCity(action.cityName)
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun saveCity(cityName: String) {
        if (cityName.isNotEmpty()) {
            viewModelScope.launch {
                saveCityUseCase(cityName)
            }
        }
    }

    private fun observeSearchQuery() {
        state.map { it.searchQuery }
            .filter { it.isNotEmpty() }
            .distinctUntilChanged()
            .debounce(SEARCH_DEBOUNCE_MS)
            .flatMapLatest { query ->
                flow<Unit> {
                    when {
                        query.isBlank() -> {
                            _state.update {
                                it.copy(
                                    cities = cities
                                )
                            }
                        }
                        query.length >= MIN_QUERY_LENGTH -> {
                            searchCity(query)
                        }
                    }
                }
            }.launchIn(viewModelScope)
    }


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun searchCity(cityName: String) = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        try {
            val result = searchCityUseCase(SearchRequestModel(cityName))
            _state.update {
                it.copy(
                    cities = result,
                    isLoading = false
                )
            }
        } catch (e: Exception) {
            _state.update {
                it.copy(
                    errorMessage = e.message,
                    isLoading = false
                )
            }
        }

    }

    private companion object {
        const val SEARCH_DEBOUNCE_MS = 500L
        const val MIN_QUERY_LENGTH = 2
    }
}
