package org.bad_coder.countries.ui.screens.presentation_screen

import android.view.View.OnClickListener
import androidx.lifecycle.ViewModelProvider
import org.bad_coder.countries.R
import org.bad_coder.countries.databinding.FragmentPresentationBinding
import org.bad_coder.countries.domain.ViewModelFactory
import org.bad_coder.countries.ui.common.BaseFragment

class PresentationFragment : BaseFragment<
        FragmentPresentationBinding,
        PresentationViewModel>(R.layout.fragment_presentation) {

    override fun initView() {
        binding.onClickListener = OnClickListener {
            // todo: Handle on click
        }
    }

    override fun createViewModel(): PresentationViewModel {
        return ViewModelProvider(this, ViewModelFactory)[PresentationViewModel::class.java]
    }
}