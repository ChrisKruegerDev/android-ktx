package com.placebox.ktx.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

val LiveData<Int?>.intValue get() = value ?: 0

fun LiveData<Int?>.bindWithZero(owner: LifecycleOwner, onChange: (Int) -> Unit) {
    observe(owner, Observer { onChange(it ?: 0) })
}
