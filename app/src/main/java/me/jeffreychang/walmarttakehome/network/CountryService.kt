package me.jeffreychang.walmarttakehome.network

import me.jeffreychang.walmarttakehome.model.CountryDtoItem
import retrofit2.http.GET
import retrofit2.http.Url


interface CountryService {

    @GET
    suspend fun getCountries(@Url directLink: String = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json"): List<CountryDtoItem>

}