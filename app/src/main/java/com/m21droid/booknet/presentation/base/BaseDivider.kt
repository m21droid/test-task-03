package com.m21droid.booknet.presentation.base

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.m21droid.booknet.R

class BaseDivider(
    context: Context,
    withPadding: Boolean = false,
) : RecyclerView.ItemDecoration() {

    private val drawable = ContextCompat.getDrawable(context, R.drawable.item_divider)
    private val padding = if (withPadding) 10 else 0

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        drawable?.apply {
            val left = parent.paddingLeft + padding
            val right = parent.width - parent.paddingRight - padding
            val childCount = parent.childCount - 1
            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams
                val top = child.bottom + params.bottomMargin
                val bottom = top + intrinsicHeight
                setBounds(left, top, right, bottom)
                draw(c)
            }
        } ?: super.onDraw(c, parent, state)
    }

}