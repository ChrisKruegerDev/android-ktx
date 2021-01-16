package com.moviebase.ktx.content

import android.content.Context
import android.content.res.Resources

val Int.dp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

// TODO: 1/13/21 remove this
fun Float.toPx(resources: Resources) = (this * resources.displayMetrics.density + 0.5f).toInt()
fun Float.toPx(context: Context) = toPx(context.resources)
fun Int.toPx(context: Context) = toFloat().toPx(context.resources)
fun Int.toPx(resources: Resources) = toFloat().toPx(resources)
