package org.bad_coder.countries.ui.screens.all_countries_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.bad_coder.countries.data.Resource
import org.bad_coder.countries.domain.model.Country
import org.bad_coder.countries.domain.usecases.GetAllCountries

class AllCountriesViewModel(
    private val getAllCountries: GetAllCountries
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState as StateFlow<UiState>

    init {
        viewModelScope.launch {
            retrieveAllCountries()
        }
    }

    private suspend fun retrieveAllCountries() {
        getAllCountries().collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.value = UiState.Loading
                }
                is Resource.Success -> {
                    _uiState.value = UiState.Success(result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _uiState.value = UiState.Error(result.message ?: "Unknown Error")
                }
            }
        }
    }


    sealed class UiState{
        object Loading: UiState()
        data class Success(val data: List<Country>): UiState()
        data class Error(val msg: String): UiState()
    }

}