package com.djevtic.myswifttestcode.extensions

import android.view.View


inline fun View.visibleIf(crossinline predicate:() -> Boolean) {
    if (predicate.invoke()) visible() else gone()
}

inline fun View.goneIf(crossinline predicate:() -> Boolean) {
    if (predicate.invoke()) gone() else visible()
}

fun View.visible() { visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() { visibility = View.GONE
}

fun View.enable() {
    isEnabled = true
}

fun View.enableIf(predicate: () -> Boolean) {
    isEnabled = predicate()
}

fun View.disable() {
    isEnabled = false
}