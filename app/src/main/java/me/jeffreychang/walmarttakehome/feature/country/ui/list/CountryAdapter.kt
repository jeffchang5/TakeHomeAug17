package me.jeffreychang.walmarttakehome.feature.country.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import me.jeffreychang.walmarttakehome.databinding.ItemCountryBinding
import me.jeffreychang.walmarttakehome.model.Country
import me.jeffreychang.walmarttakehome.model.CountryDtoItem


class CountryAdapter : ListAdapter<Country, CountryViewHolder>(CountryItemDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CountryViewHolder((binding))

    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}