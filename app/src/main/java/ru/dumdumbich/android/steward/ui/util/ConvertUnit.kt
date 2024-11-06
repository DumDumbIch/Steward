package ru.dumdumbich.android.steward.ui.util

import android.content.res.Resources
import android.util.DisplayMetrics
import kotlin.math.roundToInt

object ConvertUnit {

    private val displayMetrics: DisplayMetrics = Resources.getSystem().displayMetrics

    val Number.dpToPxFloat: Float
        get() = (this.toFloat() * displayMetrics.density)

    val Number.dpToPx: Int
        get() = this.dpToPxFloat.roundToInt()

}
