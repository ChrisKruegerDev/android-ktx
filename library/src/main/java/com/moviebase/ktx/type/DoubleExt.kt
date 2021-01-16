package com.moviebase.ktx.type

val Double.alpha: Int get() = (this * 255).toInt()
