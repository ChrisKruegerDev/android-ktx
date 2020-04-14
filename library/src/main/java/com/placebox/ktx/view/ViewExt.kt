package com.placebox.ktx.view

import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import androidx.annotation.IdRes
import com.placebox.ktx.R
import com.placebox.ktx.content.hideKeyboard


inline fun <reified T : View> View.viewId(@IdRes id: Int): Lazy<T> = lazy { findViewById<T>(id) }

fun View.hideKeyboard() = context.hideKeyboard(windowToken)

fun View.updateEnabled(enable: Boolean, alphaValue: Double = .4) {
    isEnabled = enable

    if (enable) {
        alpha = 1f
        visibility = View.VISIBLE
    } else {
        alpha = alphaValue.toFloat()
    }
}


fun View.setMarginTop(margin: Int) {
    val params = layoutParams as ViewGroup.MarginLayoutParams
    params.setMargins(params.leftMargin, margin, params.rightMargin, params.bottomMargin)
}