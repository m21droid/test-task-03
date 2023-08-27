package com.m21droid.booknet.presentation.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BaseSpace(
    private val space: Int,
    private val spaceLast: Int = 0,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val itemCount = parent.adapter?.itemCount ?: return
        val boolean = itemCount.minus(1) != parent.getChildAdapterPosition(view)
        outRect.bottom = if (boolean) space else spaceLast
    }

}