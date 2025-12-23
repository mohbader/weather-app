package com.weather.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.datastore.usecases.IsCitySavedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isCityUseCase: IsCitySavedUseCase
) : ViewModel() {

    private val _isCitySavedState = MutableStateFlow<Boolean?>(null)
    var isCitySavedState = _isCitySavedState.asStateFlow()

    init {
       checkSaveCity()
    }

    private fun checkSaveCity() {
        viewModelScope.launch {
            isCityUseCase().collect { isCitySaved ->
                _isCitySavedState.value = isCitySaved
            }
        }
    }


}