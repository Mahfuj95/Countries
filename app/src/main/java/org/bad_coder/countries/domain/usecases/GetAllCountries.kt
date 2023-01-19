package org.bad_coder.countries.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.bad_coder.countries.data.Resource
import org.bad_coder.countries.data.model.CountryDto
import org.bad_coder.countries.data.network.CountriesApi
import org.bad_coder.countries.domain.model.Country

class GetAllCountries(private val apiService: CountriesApi) {

    suspend operator fun invoke(): Flow<Resource<List<Country>>> = flow {
        emit(Resource.Loading())
        val result = handleNetworkCall(
            { apiService.getAllCountries() },
            { parseCountryList(it) }
        )
        emit(result)
    }

    private fun parseCountryList(response: List<CountryDto>?): Resource.Success<List<Country>> {
        return Resource.Success(response?.map { dto ->
            Country(
                name = dto.name?.common ?: "Unknown",
                capital = dto.capital?.toString() ?: "Unknown",
                flag = dto.flags?.png ?: "Unknown",
                region = dto.region ?: "Unknown"
            )
        } ?: listOf())
    }
}