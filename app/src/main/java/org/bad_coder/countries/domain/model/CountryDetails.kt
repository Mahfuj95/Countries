package org.bad_coder.countries.domain.model

data class CountryDetails(
    val area: Double? = null,
    val capital: String? = null,
    val flag: String? = null,
    val name: String? = null,
    val population: Int? = null,
    val region: String? = null,
    val subregion: String? = null,
){
    override fun toString(): String {
        return "Capital:\t\t\t$capital\n" +
               "Area:\t\t\t$area\n" +
               "Population:\t\t\t$population\n" +
               "Region:\t\t\t$region\n" +
               "Subregion:\t\t\t$subregion\n"
    }
}