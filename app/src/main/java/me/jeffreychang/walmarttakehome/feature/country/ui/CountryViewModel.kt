package me.jeffreychang.walmarttakehome.feature.country.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import me.jeffreychang.walmarttakehome.model.CountryItem
import me.jeffreychang.walmarttakehome.network.CountryNetworkModule
import me.jeffreychang.walmarttakehome.repo.CountryRepository
import me.jeffreychang.walmarttakehome.repo.GistCountryRepository
import me.jeffreychang.walmarttakehome.util.AppContextProvider
import me.jeffreychang.walmarttakehome.util.ContextProvider


class CountryViewModel(
    private val contextProvider: ContextProvider,
    private val countryRepository: CountryRepository
) : ViewModel() {

    sealed class CountryUiState {
        data object Loading : CountryUiState()

        data class Success(val countries: List<CountryItem>) : CountryUiState()

        data class Error(val t: Throwable) : CountryUiState()

    }

    private val uiState: MutableLiveData<CountryUiState> = MutableLiveData()

    fun uiState(): LiveData<CountryUiState> = uiState

    fun getCountries() {
        uiState.postValue(CountryUiState.Loading)
        viewModelScope.launch(contextProvider.io) {
            try {
                val countries = countryRepository.getCountries()
                uiState.postValue(CountryUiState.Success(countries))
            } catch (t: Throwable) {
                uiState.postValue(CountryUiState.Error(t))
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