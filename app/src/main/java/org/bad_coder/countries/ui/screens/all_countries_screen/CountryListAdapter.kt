package org.bad_coder.countries.ui.screens.all_countries_screen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import org.bad_coder.countries.BR
import org.bad_coder.countries.databinding.CountryListItemBinding
import org.bad_coder.countries.domain.model.Country

class CountryListAdapter : RecyclerView.Adapter<MainViewHolder>() {
    var countries = listOf<Country>()
    private var onClickListener: OnItemClickEvent? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setCountryList(countries: List<Country>) {
        this.countries = countries
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClickListener: OnItemClickEvent){
        this.onClickListener = onClickListener
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(
            CountryListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.apply {
            bind(countries[position], onClickListener)
        }
    }

    override fun getItemCount(): Int {
        return countries.size
    }
}

class MainViewHolder(private val binding: CountryListItemBinding)
    : RecyclerView.ViewHolder(binding.root) {
    fun bind(country: Country, onClickListener: OnItemClickEvent?) {
        binding.setVariable(BR.country, country)
        binding.setVariable(BR.onClickListener, onClickListener)
        binding.executePendingBindings()
    }
}

interface OnItemClickEvent {
    fun onClick(view: View, itemModel: Country)
}