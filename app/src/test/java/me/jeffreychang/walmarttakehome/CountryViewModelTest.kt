package me.jeffreychang.walmarttakehome

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import me.jeffreychang.walmarttakehome.feature.country.ui.CountryViewModel
import me.jeffreychang.walmarttakehome.model.Country
import me.jeffreychang.walmarttakehome.repo.CountryRepository
import me.jeffreychang.walmarttakehome.util.ResultOf
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CountryViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()


    private lateinit var vm: CountryViewModel

    @Test
    fun `getCountries return a success`() = runBlocking {
        val fakeRepo = mockk<CountryRepository> {
            coEvery { getCountries() } returns ResultOf.Success(
                listOf(
                    Country(
                        "name",
                        "region",
                        "code",
                        "capital"
                    )
                )
            )
        }
        vm = CountryViewModel(TestContextProvider, fakeRepo)
        vm.getCountries()

        val result = vm.uiState().value
        assert(
            result is CountryViewModel.CountryUiState.Success && result.countries.size == 1
        )
    }


    @Test
    fun `getCountries with delay return loading`() = runBlocking {
        val fakeRepo = mockk<CountryRepository> {
            coEvery { getCountries() } coAnswers {
                delay(500)
                ResultOf.Success(listOf())
            }
        }
        vm = CountryViewModel(TestContextProvider, fakeRepo)
        vm.getCountries()

        val result = vm.uiState().value
        assert(result is CountryViewModel.CountryUiState.Loading)
    }

    @Test
    fun `getCountries with throw return error`() = runBlocking {
        val fakeRepo = mockk<CountryRepository> {
            coEvery { getCountries() } returns ResultOf.Failure("message", IOException())
        }
        vm = CountryViewModel(TestContextProvider, fakeRepo)
        vm.getCountries()

        val result = vm.uiState().value
        assert(result is CountryViewModel.CountryUiState.Error)
    }
}