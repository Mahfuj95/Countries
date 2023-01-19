package org.bad_coder.countries.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.bad_coder.countries.ui.screens.presentation_screen.PresentationViewModel

object ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PresentationViewModel::class.java)) {
            return PresentationViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}