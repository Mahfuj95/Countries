package org.bad_coder.countries.ui.screens.all_countries_screen

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import org.bad_coder.countries.R
import org.bad_coder.countries.databinding.FragmentAllCountriesBinding
import org.bad_coder.countries.domain.ViewModelFactory
import org.bad_coder.countries.domain.model.Country
import org.bad_coder.countries.ui.common.BaseFragment
import org.bad_coder.countries.ui.common.getNavController
import org.bad_coder.countries.ui.common.hide
import org.bad_coder.countries.ui.common.show
import org.bad_coder.countries.ui.screens.all_countries_screen.AllCountriesViewModel.UiState

class AllCountriesFragment : BaseFragment<
        FragmentAllCountriesBinding,
        AllCountriesViewModel>(R.layout.fragment_all_countries) {

    private var adapter: CountryListAdapter? = null

    override fun initView() {
        CountryListAdapter().apply {
            setOnClickListener(object : OnItemClickEvent {
                override fun onClick(view: View, itemModel: Country) {
                    val directions = AllCountriesFragmentDirections
                        .actionAllCountriesFragmentToCountryDetailsFragment(itemModel.name)
                    getNavController().navigate(directions)
                }
            })
            adapter = this
            binding.countryList.adapter = adapter
        }

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest { uiState ->
                renderView(uiState)
            }
        }
    }

    private fun renderView(uiState: UiState) {
        when (uiState) {
            is UiState.Success -> {
                binding.loadingBar.hide()
                adapter!!.setCountryList(uiState.data)
            }
            is UiState.Loading -> {
                binding.loadingBar.show()
                binding.errorScreen.hide()
            }
            is UiState.Error -> {
                binding.loadingBar.hide()
                binding.countryList.hide()

                binding.errorMsg.text = uiState.msg
                binding.errorScreen.show()
            }
        }
    }

    override fun createViewModel(): AllCountriesViewModel {
        return ViewModelProvider(this, ViewModelFactory)[AllCountriesViewModel::class.java]
    }
}