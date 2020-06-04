package com.placebox.ktx.lifecycle

import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.placebox.ktx.view.updateText

fun <T : CharSequence?> LiveData<T>.bindTextView(owner: LifecycleOwner, textView: TextView) =
    bind(owner) { textView.updateText(it) }
