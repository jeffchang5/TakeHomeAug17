package me.jeffreychang.walmarttakehome.feature.country.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import me.jeffreychang.walmarttakehome.model.Country
import me.jeffreychang.walmarttakehome.model.CountryDtoItem
import me.jeffreychang.walmarttakehome.network.CountryNetworkModule
import me.jeffreychang.walmarttakehome.repo.CountryRepository
import me.jeffreychang.walmarttakehome.repo.GistCountryRepository
import me.jeffreychang.walmarttakehome.util.AppContextProvider
import me.jeffreychang.walmarttakehome.util.ContextProvider
import me.jeffreychang.walmarttakehome.util.ResultOf


class CountryViewModel(
    private val contextProvider: ContextProvider,
    private val countryRepository: CountryRepository
) : ViewModel() {

    sealed class CountryUiState {
        data object Loading : CountryUiState()

        data class Success(val countries: List<Country>) : CountryUiState()

        data class Error(val t: Throwable) : CountryUiState()

    }

    private val uiState: MutableLiveData<CountryUiState> = MutableLiveData()

    fun uiState(): LiveData<CountryUiState> = uiState

    fun getCountries() {
        uiState.postValue(CountryUiState.Loading)
        viewModelScope.launch(contextProvider.io) {
            when (val result = countryRepository.getCountries()) {
                is ResultOf.Success -> {
                    uiState.postValue(CountryUiState.Success(result.value))
                }

                is ResultOf.Failure -> {
                    uiState.postValue(CountryUiState.Error(result.throwable))
                }
            }
        }
    }

    companion object {

        private val countryService = CountryNetworkModule.countryService()

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                return CountryViewModel(
                    AppContextProvider,
                    GistCountryRepository(countryService)
                ) as T
            }
        }
    }
}