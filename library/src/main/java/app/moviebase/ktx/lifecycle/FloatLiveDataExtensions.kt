package app.moviebase.ktx.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun LiveData<Float?>.bindWithZero(owner: LifecycleOwner, onChange: (Float) -> Unit) =
    bind(owner) { onChange(it ?: 0f) }