package org.bad_coder.countries.ui.common

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import org.bad_coder.countries.R

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun Fragment.getNavController(): NavController{
    val navHostFragment = requireActivity().supportFragmentManager
        .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    return navHostFragment.navController
}