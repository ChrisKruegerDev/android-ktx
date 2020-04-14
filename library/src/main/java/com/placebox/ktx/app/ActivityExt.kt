package com.placebox.ktx.app

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.app.ShareCompat
import kotlin.reflect.KClass

fun Activity.startActivityWithFade(intent: Intent) {
    startActivity(intent, makeFadeAnimation())
}

fun Activity.startActivityWithFade(cls: KClass<*>) = startActivityWithFade(Intent(this, cls.java))

fun Activity.makeFadeAnimation(): Bundle {
    val options = ActivityOptions.makeCustomAnimation(this, android.R.anim.fade_in, android.R.anim.fade_out)
    return options.toBundle()
}

fun Activity.makeSceneTransitionAnimation(view: View): Bundle? {
    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, view.transitionName)
    return options.toBundle()
}

val Activity.view: View? get() = findViewById(android.R.id.content)

fun Activity.startShareChooser(uri: String, title: String?) {
    ShareCompat.IntentBuilder.from(this)
        .setText(uri)
        .setSubject(title)
        .setType("text/plain")
        .startChooser()
}

fun Activity.startChooser(intent: Intent, title: String?) {
    val chooser = Intent.createChooser(intent, title)
    startActivity(chooser)
}
