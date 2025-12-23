package com.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.datastore.usecases.SaveCityUseCase
import com.weather.domain.model.SearchRequestModel
import com.weather.domain.model.CityModel
import com.weather.domain.usecase.SearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val saveCityUseCase: SaveCityUseCase,
    private val searchCityUseCase: SearchCityUseCase
) : ViewModel() {

    private val _searchState = MutableStateFlow<List<CityModel>?>(null)
    val searchState = _searchState.asStateFlow()

    fun saveCity(cityName: String) {
        viewModelScope.launch {
            saveCityUseCase(cityName)
        }
    }

    fun searchCity(cityName: String) {
        viewModelScope.launch {
            _searchState.value = searchCityUseCase(SearchRequestModel(cityName))
//          runCatching{
//              searchCityUseCase(SearchRequestModel(cityName))
//          }.onSuccess {
//              _searchState.value = searchCityUseCase(SearchRequestModel(cityName))
//          }.onFailure {
//
//            }
        }
    }

}