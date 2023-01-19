package org.bad_coder.countries.data.model

data class CountryDto(
    val area: Double?,
    val capital: List<String>?,
    val flags: Flag?,
    val name: CountryName?,
    val population: Int?,
    val region: String?,
    val subregion: String?
)

data class CountryName(
    val common: String,
    val official: String
)

data class Flag(
    val png: String,
    val svg: String
)