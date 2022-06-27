package com.heymaster.heymaster.utils.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

