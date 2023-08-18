package me.jeffreychang.walmarttakehome.repo

import me.jeffreychang.walmarttakehome.model.CountryItem
import me.jeffreychang.walmarttakehome.network.CountryService

class GistCountryRepository(
    private val countryService: CountryService
) : CountryRepository {

    override suspend fun getCountries(): List<CountryItem> {
        return countryService.getCountries()
    }
}

interface CountryRepository {

    suspend fun getCountries(): List<CountryItem>

}