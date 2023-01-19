package org.bad_coder.countries.ui.screens.country_details_screen

import androidx.lifecycle.ViewModelProvider
import org.bad_coder.countries.R
import org.bad_coder.countries.databinding.FragmentCountryDetailsBinding
import org.bad_coder.countries.domain.ViewModelFactory
import org.bad_coder.countries.ui.common.BaseFragment

class CountryDetailsFragment : BaseFragment<
        FragmentCountryDetailsBinding,
        CountryDetailsViewModel>(R.layout.fragment_country_details) {

    override fun initView() {
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }

    override fun createViewModel(): CountryDetailsViewModel {
        return ViewModelProvider(this, ViewModelFactory)[CountryDetailsViewModel::class.java]
    }
}