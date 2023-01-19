package org.bad_coder.countries.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.bad_coder.countries.data.network.RetrofitHelper
import org.bad_coder.countries.domain.usecases.GetAllCountries
import org.bad_coder.countries.domain.usecases.GetCountryDetailsByName
import org.bad_coder.countries.ui.screens.all_countries_screen.AllCountriesViewModel
import org.bad_coder.countries.ui.screens.country_details_screen.CountryDetailsViewModel
import org.bad_coder.countries.ui.screens.presentation_screen.PresentationViewModel

object ViewModelFactory : ViewModelProvider.Factory {
    private val getAllCountries: GetAllCountries by lazy {
        GetAllCountries(RetrofitHelper.countryApi)
    }

    private val getCountryDetailsByCountryName: GetCountryDetailsByName by lazy {
        GetCountryDetailsByName(RetrofitHelper.countryApi)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PresentationViewModel::class.java)) {
            return PresentationViewModel() as T
        } else if (modelClass.isAssignableFrom(AllCountriesViewModel::class.java)) {
            return AllCountriesViewModel(getAllCountries) as T
        } else if (modelClass.isAssignableFrom(CountryDetailsViewModel::class.java)) {
            return CountryDetailsViewModel(getCountryDetailsByCountryName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}