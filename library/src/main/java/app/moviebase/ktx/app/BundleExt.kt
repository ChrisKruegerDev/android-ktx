package app.moviebase.ktx.app

import android.os.Bundle

inline fun bundle(block: Bundle.() -> Unit) = Bundle().apply(block)
