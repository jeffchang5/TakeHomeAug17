package me.jeffreychang.walmarttakehome.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit


object CountryNetworkModule {

    private fun provideKotlinSerialization() = Json.asConverterFactory(
        MediaType.parse("application/json")!!
    )

    private fun provideRetrofit() = Retrofit.Builder().baseUrl("https://api.github.com/")
        .addConverterFactory(provideKotlinSerialization()).build()

    fun countryService(): CountryService {
        return provideRetrofit().create(CountryService::class.java)
    }

}