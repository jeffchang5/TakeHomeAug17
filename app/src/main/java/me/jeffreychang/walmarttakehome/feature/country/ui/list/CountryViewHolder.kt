package me.jeffreychang.walmarttakehome.feature.country.ui.list

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import me.jeffreychang.walmarttakehome.R
import me.jeffreychang.walmarttakehome.databinding.ItemCountryBinding
import me.jeffreychang.walmarttakehome.model.CountryItem

class CountryViewHolder(private val binding: ItemCountryBinding) : ViewHolder(binding.root) {

    private val context = binding.root.context

    fun bind(item: CountryItem) {
        binding.apply {
            codeTextView.text = item.code
            nameTextView.text = context.getString(R.string.place_template, item.name, item.region)
            capitalTextView.text = item.capital
        }
    }
}