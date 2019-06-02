package com.dimchel.annatest.common

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dimchel.annatest.R

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

fun Fragment.navController(): NavController =
    Navigation.findNavController(activity!!, R.id.fragment_launch)