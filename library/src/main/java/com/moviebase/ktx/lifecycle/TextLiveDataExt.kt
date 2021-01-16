package com.moviebase.ktx.lifecycle

import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.moviebase.ktx.view.updateText

fun <T : CharSequence?> LiveData<T>.bindTextView(owner: LifecycleOwner, textView: TextView) =
    bind(owner) { textView.updateText(it) }
