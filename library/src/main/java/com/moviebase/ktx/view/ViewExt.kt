package com.moviebase.ktx.view

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.moviebase.ktx.content.hideKeyboard
import kotlin.reflect.KClass


inline fun <reified T : View> View.viewId(@IdRes id: Int): Lazy<T> = lazy { findViewById<T>(id) }

inline fun <reified T : View> View.findParent() = findParentOf(T::class)

@Suppress("UNCHECKED_CAST")
tailrec fun <T : View> View.findParentOf(clazz: KClass<T>): T? {
    val viewParent = parent
    if (viewParent == null || viewParent !is View) return null
    if (clazz.isInstance(viewParent)) return viewParent as T
    return viewParent.findParentOf(clazz)
}

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

var View.marginTopValue
    get() = (layoutParams as? ViewGroup.MarginLayoutParams)?.topMargin ?: (layoutParams as? ConstraintLayout.LayoutParams)?.topMargin ?: 0
    set(value) {
        (layoutParams as? ViewGroup.MarginLayoutParams)?.let {
            it.setMargins(it.leftMargin, value, it.rightMargin, it.bottomMargin)
        }
        (layoutParams as? ConstraintLayout.LayoutParams)?.let {
            it.topMargin = value
            this.layoutParams = it
        }
    }
