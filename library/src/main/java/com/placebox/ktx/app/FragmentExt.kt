package com.placebox.ktx.app

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.placebox.ktx.content.preferences

fun Fragment.registerOnSharedPreferenceChangeListener(l: SharedPreferences.OnSharedPreferenceChangeListener) {
    requireContext().preferences.registerOnSharedPreferenceChangeListener(l)
}

fun Fragment.unregisterOnSharedPreferenceChangeListener(l: SharedPreferences.OnSharedPreferenceChangeListener) {
    requireContext().preferences.unregisterOnSharedPreferenceChangeListener(l)
}

val Fragment.isAttached: Boolean get() = activity != null && isAdded
val Fragment.isNotAttached: Boolean get() = !isAttached

inline fun <T : Fragment> T.applyArguments(action: Bundle.() -> Unit): T {
    arguments = Bundle().apply { action(this) }
    return this
}

fun Fragment.requireAppCompatActivity() = requireActivity() as AppCompatActivity