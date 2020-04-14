package com.placebox.ktx.lifecycle

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.placebox.ktx.type.isNullOrFalse
import com.placebox.ktx.type.isTrue

val LiveData<Boolean?>.booleanValue: Boolean
    get() = value.isTrue()

fun LiveData<Boolean?>.bindVisible(owner: LifecycleOwner, view: View) {
    observe(owner, Observer { view.isVisible = it.isTrue() })
}

fun LiveData<Boolean?>.bindVisibleOrInvisible(owner: LifecycleOwner, view: View) {
    observe(owner, Observer { view.isInvisible = it.isNullOrFalse() })
}

fun LiveData<Boolean?>.bindVisible(owner: LifecycleOwner, vararg views: View) {
    observe(owner, Observer {
        views.forEach { view -> view.isVisible = it.isTrue() }
    })
}

fun LiveData<Boolean?>.bindOnTrue(owner: LifecycleOwner, onChange: () -> Unit) {
    observe(owner, Observer { if (it.isTrue()) onChange() })
}
