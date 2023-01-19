package org.bad_coder.countries.ui.screens.country_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.bad_coder.countries.data.Resource
import org.bad_coder.countries.domain.model.CountryDetails
import org.bad_coder.countries.domain.usecases.GetCountryDetailsByName

class CountryDetailsViewModel(
    private val getCountryDetailsByName: GetCountryDetailsByName
): ViewModel() {

    private val _countryDetails = MutableStateFlow<CountryDetails?>(null)
    var countryDetails : StateFlow<CountryDetails?> = _countryDetails

    private val _isLoading = MutableStateFlow<Boolean>(false)
    var isLoading : StateFlow<Boolean> = _isLoading

    fun getCountryDetails(countryName: String){
        viewModelScope.launch {
            getCountryDetailsByName(countryName).collect{ result ->
                _isLoading.value = result is Resource.Loading

                if(result is Resource.Success){
                    _countryDetails.value = result.data
                }
            }
        }
    }
}