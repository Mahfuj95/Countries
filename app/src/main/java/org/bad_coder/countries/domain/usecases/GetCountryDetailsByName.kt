package org.bad_coder.countries.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.bad_coder.countries.data.Resource
import org.bad_coder.countries.data.model.CountryDto
import org.bad_coder.countries.data.network.CountriesApi
import org.bad_coder.countries.domain.model.CountryDetails

class GetCountryDetailsByName(private val apiService: CountriesApi) {
    suspend operator fun invoke(countryName: String): Flow<Resource<CountryDetails>> = flow {
        emit(Resource.Loading())
        val result = handleNetworkCall(
            { apiService.getCountryByName(countryName) },
            {
                if (it != null) parseCountryDetails(it[0])
                else Resource.Error("Parsing Error")
            })
        emit(result)
    }

    private fun parseCountryDetails(details: CountryDto): Resource.Success<CountryDetails> {
        return Resource.Success(
            CountryDetails(
                area = details.area,
                capital = details.capital?.toString() ?: "Unknown",
                flag = details.flags?.png,
                name = details.name?.common,
                population = details.population ?: 0,
                region = details.region,
                subregion = details.subregion,
            )
        )
    }
}