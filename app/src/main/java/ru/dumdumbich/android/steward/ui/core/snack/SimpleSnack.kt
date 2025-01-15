package ru.dumdumbich.android.steward.ui.core.snack

import android.view.View
import com.google.android.material.snackbar.Snackbar


class SimpleSnack(private val view: View) {

    fun show(message: String) {
        Snackbar.make(
            view,
            message,
            Snackbar.LENGTH_INDEFINITE
        ).apply {
            show()
        }
    }
}
