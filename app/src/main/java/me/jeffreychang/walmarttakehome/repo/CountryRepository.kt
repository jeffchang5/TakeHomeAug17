package me.jeffreychang.walmarttakehome.repo

import me.jeffreychang.walmarttakehome.model.Country
import me.jeffreychang.walmarttakehome.network.CountryService
import me.jeffreychang.walmarttakehome.util.ResultOf

class GistCountryRepository(
    private val countryService: CountryService
) : CountryRepository {

    override suspend fun getCountries(): ResultOf<List<Country>> {
        return try {
            val result = countryService.getCountries()
            ResultOf.Success(result).toDomain { dto ->
                dto.map {
                    Country(it.name, it.region, it.code, it.capital)
                }
            }
        } catch (t: Throwable) {
            ResultOf.Failure("Failed to get countries", t)
        }
    }
}

interface CountryRepository {

    suspend fun getCountries(): ResultOf<List<Country>>

}