package br.com.nicolas.atentabrasil.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment


fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    requireContext().toast(message, duration)
}