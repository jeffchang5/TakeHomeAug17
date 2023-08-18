package me.jeffreychang.walmarttakehome.feature.country.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import me.jeffreychang.walmarttakehome.databinding.FragmentCountryBinding
import me.jeffreychang.walmarttakehome.feature.country.ui.list.CountryAdapter
import me.jeffreychang.walmarttakehome.util.Logger

private const val TAG = "CountryFragment"

/**
 * A simple [Fragment] that shows a list of countries
 */
class CountryFragment : Fragment() {


    private lateinit var binding: FragmentCountryBinding

    private val viewModel: CountryViewModel by viewModels { CountryViewModel.Factory }

    private val adapter by lazy {
        CountryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountryBinding.inflate(inflater, container, false).apply {
            fragmentCountryRecyclerView.adapter = adapter
            fragmentCountryRecyclerView.layoutManager = LinearLayoutManager(context)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeLiveData()
        viewModel.getCountries()
    }

    private fun subscribeLiveData() {
        binding.apply {
            viewModel.uiState().observe(viewLifecycleOwner) {
                fragmentCountryProgressBar.isVisible = it is CountryViewModel.CountryUiState.Loading
                fragmentCountryRecyclerView.isVisible =
                    it is CountryViewModel.CountryUiState.Success
                fragmentCountryErrorScreen.isVisible = it is CountryViewModel.CountryUiState.Error

                when (it) {
                    is CountryViewModel.CountryUiState.Loading -> {
                        Logger.d(TAG, "Started to load countries")
                    }

                    is CountryViewModel.CountryUiState.Success -> {
                        Logger.d(TAG, "Loaded countries successfully")
                        adapter.submitList(it.countries)
                    }

                    is CountryViewModel.CountryUiState.Error -> {
                        Logger.e(TAG, "Ran into an error loading a list of countries")
                    }
                }
            }
        }
    }
}