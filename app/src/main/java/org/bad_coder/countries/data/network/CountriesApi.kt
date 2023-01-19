package org.bad_coder.countries.data.network

import org.bad_coder.countries.data.model.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApi {
    @GET("all")
    suspend fun getAllCountries(): List<CountryDto>

    @GET("name/{country_name}")
    suspend fun getCountryByName(@Path("country_name") countryName: String): List<CountryDto>
}