package me.jeffreychang.walmarttakehome.feature.country.ui.list

import androidx.recyclerview.widget.DiffUtil
import me.jeffreychang.walmarttakehome.model.Country
import me.jeffreychang.walmarttakehome.model.CountryDtoItem

class CountryItemDiffUtil : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }
}
