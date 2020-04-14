package com.placebox.ktx.lifecycle

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

val <T> LiveData<T?>.requireValue: T
    get() = value ?: throw NullPointerException("value is not available")

fun <T> MutableLiveData<T>.trigger() {
    value = value
}

fun <T> MutableLiveData<T>.updateValue(newValue: T) {
    if (value != newValue) value = newValue
}

fun <T> LiveData<T>.bind(owner: LifecycleOwner, onChange: (T?) -> Unit) {
    val lifecycleOwner = if (owner is Fragment) owner.viewLifecycleOwner else owner
    observe(lifecycleOwner, Observer { onChange(it) })
}

fun <T> LiveData<T>.bindNotNull(owner: LifecycleOwner, onChange: (T) -> Unit) =
    observe(owner, Observer { if (it != null) onChange(it) })

fun <T : Any?> LiveData<T>.bindNullableAsSelected(owner: LifecycleOwner, view: View) {
    observe(owner, Observer { view.isSelected = it != null })
}

fun <T : Any?> LiveData<T>.bindNullableAsVisible(owner: LifecycleOwner, view: View) {
    observe(owner, Observer { view.isVisible = it != null })
}
