package com.m21droid.booknet

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL

fun Any.logD(msg: String?) {
    if (BuildConfig.DEBUG) Log.d(javaClass.simpleName, msg ?: return)
}

fun Any.logE(msg: String?) {
    if (BuildConfig.DEBUG) Log.e(javaClass.simpleName, msg ?: return)
}

fun Any.logI(msg: String?) {
    if (BuildConfig.DEBUG) Log.i(javaClass.simpleName, msg ?: return)
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun RecyclerView.init(adapter: Adapter<*>, divider: ItemDecoration?, orientation: Int = VERTICAL) {
    layoutManager = when (orientation) {
        HORIZONTAL, VERTICAL -> LinearLayoutManager(context, orientation, false)
        else -> GridLayoutManager(context, orientation)
    }
    if (divider != null) addItemDecoration(divider)
    this.adapter = adapter
}