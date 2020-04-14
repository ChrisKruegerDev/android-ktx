package com.placebox.ktx.widget

import androidx.annotation.MenuRes
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI

fun Toolbar.setOnMenuItemClick(@MenuRes menuRes: Int = 0, onMenuItemClick: (Int) -> Unit) {
    if (menuRes != 0) inflateMenu(menuRes)
    setOnMenuItemClickListener { onMenuItemClick(it.itemId); true }
}

fun Toolbar.setupWithNavController(navController: NavController) =
        NavigationUI.setupWithNavController(this, navController)
