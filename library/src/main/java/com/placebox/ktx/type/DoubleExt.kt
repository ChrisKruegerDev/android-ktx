package com.placebox.ktx.type

val Double.alpha: Int get() = (this * 255).toInt()
