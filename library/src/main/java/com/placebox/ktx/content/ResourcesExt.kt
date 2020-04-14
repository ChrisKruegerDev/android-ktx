package com.placebox.ktx.content

import android.content.Context
import android.content.res.Resources

fun Float.toPx(resources: Resources) = (this * resources.displayMetrics.density + 0.5f).toInt()
fun Float.toPx(context: Context) = toPx(context.resources)
fun Int.toPx(context: Context) = toFloat().toPx(context.resources)
fun Int.toPx(resources: Resources) = toFloat().toPx(resources)
