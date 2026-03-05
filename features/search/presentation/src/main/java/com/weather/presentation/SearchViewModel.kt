package com.weather.presentation

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
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
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

    private var cites: List<CityModel> = emptyList()
    private var searchJob: Job? = null

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

    private fun saveCity(cityName: String) {
        if(cityName.isBlank().not()) {
            viewModelScope.launch {
                saveCityUseCase(cityName)
            }
        }
    }

    private fun observeSearchQuery() {
        state.map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                errorMessage = null,
                                cities = cites
                            )
                        }
                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchCity(query)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun searchCity(cityName: String) = viewModelScope.launch {
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
}