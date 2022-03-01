package br.com.nicolas.atentabrasil.utils

import android.content.Context
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment


fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    requireContext().toast(message, duration)
}

@RequiresApi(Build.VERSION_CODES.R)
fun View.hideKeyboard(view: View) {
    windowInsetsController?.hide(WindowInsets.Type.ime())
    view.clearFocus()
}