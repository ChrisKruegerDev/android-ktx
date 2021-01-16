package com.moviebase.ktx.preference

import androidx.preference.Preference

fun Preference.onPreferenceChange(block: (Any) -> Unit) {
    setOnPreferenceChangeListener { _, newValue ->
        block(newValue)
        true
    }
}
