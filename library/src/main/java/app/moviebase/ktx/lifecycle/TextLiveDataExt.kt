package app.moviebase.ktx.lifecycle

import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import app.moviebase.ktx.view.updateText

fun <T : CharSequence?> LiveData<T>.bindTextView(owner: LifecycleOwner, textView: TextView) =
    bind(owner) { textView.updateText(it) }
