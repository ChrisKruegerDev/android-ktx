package com.placebox.ktx.view

import android.widget.ProgressBar

fun ProgressBar.updateProgress(value: Int) {
    if(progress == value) return
    progress = value
}
