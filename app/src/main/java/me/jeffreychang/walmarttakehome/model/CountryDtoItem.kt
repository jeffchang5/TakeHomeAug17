package me.jeffreychang.walmarttakehome.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryDtoItem(
    @SerialName("capital")
    val capital: String,
    @SerialName("code")
    val code: String,
    @SerialName("currency")
    val currency: Currency,
    @SerialName("demonym")
    val demonym: String? = null,
    @SerialName("flag")
    val flag: String,
    @SerialName("language")
    val language: Language,
    @SerialName("name")
    val name: String,
    @SerialName("region")
    val region: String
)