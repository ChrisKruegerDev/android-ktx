package com.placebox.ktx.view

import androidx.viewpager.widget.ViewPager

fun ViewPager.onPageSelected(callback: (Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            callback(position)
        }
    })
}

fun ViewPager.updateCurrentItem(position: Int) {
    if (currentItem != position)
        currentItem = position
}
