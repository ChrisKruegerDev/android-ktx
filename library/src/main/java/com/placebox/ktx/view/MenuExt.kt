package com.placebox.ktx.view

import android.graphics.drawable.GradientDrawable
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.ColorInt
import androidx.core.text.toSpannable
import com.placebox.ktx.type.withColor

fun Menu.colorItemTitle(id: Int, @ColorInt color: Int) {
    val item = findItem(id) ?: return
    item.title = item.title.toSpannable().withColor(color)
}

fun Menu.tint(@ColorInt color: Int) {
    for (i in 0 until this.size()) {
        getItem(i).tint(color)
    }
}

fun MenuItem.tint(@ColorInt color: Int) {
    (icon as? GradientDrawable)?.setColor(color)
}
