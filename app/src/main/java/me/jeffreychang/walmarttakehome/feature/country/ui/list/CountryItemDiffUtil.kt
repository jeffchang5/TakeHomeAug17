package me.jeffreychang.walmarttakehome.feature.country.ui.list

import androidx.recyclerview.widget.DiffUtil
import me.jeffreychang.walmarttakehome.model.CountryItem

class CountryItemDiffUtil : DiffUtil.ItemCallback<CountryItem>() {
    override fun areItemsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
        return oldItem == newItem
    }
}
