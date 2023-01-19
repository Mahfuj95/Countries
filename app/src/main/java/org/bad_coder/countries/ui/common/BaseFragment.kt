package org.bad_coder.countries.ui.common

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<
        BindingType : ViewBinding,
        ViewModelType : ViewModel>(@LayoutRes val layoutRes: Int) : Fragment(layoutRes) {

    protected lateinit var binding: BindingType
    lateinit var viewModel: ViewModelType

    fun registerBackPressListener() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressed()
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = createViewModel()
        binding = DataBindingUtil.setContentView(requireActivity(), layoutRes)
        initView()
    }

    open fun onBackPressed() {}
    abstract fun initView()
    abstract fun createViewModel() : ViewModelType
}

