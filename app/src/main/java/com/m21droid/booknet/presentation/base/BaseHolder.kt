package com.m21droid.booknet.presentation.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder

/**
 * Should be used as base class for all [ViewHolder]
 *
 * @param <T> Class-model that representing adapter item </T>
 * */

abstract class BaseHolder<T>(
    recyclerView: ViewGroup,
    @LayoutRes layoutId: Int,
    viewIds: IntArray? = null,
) : ViewHolder(
    LayoutInflater.from(recyclerView.context).inflate(layoutId, recyclerView, false)
), OnClickListener {

    protected val context: Context = itemView.context
    private var listener: OnItemClickListener<T>? = null
    private var item: T? = null

    init {
        initListeners(viewIds)
    }

    private fun initListeners(viewIds: IntArray?) {
        itemView.setOnClickListener(this)
        viewIds?.forEach {
            itemView.findViewById<View>(it)?.setOnClickListener(this)
        }
    }

    internal fun bindModel(model: T) {
        this.item = model
        bindItem(model)
    }

    override fun onClick(v: View) {
        item?.let { model ->
            listener?.onItemViewClick(v, model)
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener<T>?) {
        this.listener = listener
    }

    @ColorInt
    protected fun getColor(@ColorRes colorId: Int) =
        ContextCompat.getColor(context, colorId)

    protected fun getString(@StringRes stringId: Int): String =
        context.getString(stringId)

    abstract fun bindItem(model: T)

    interface OnItemClickListener<T> {
        fun onItemViewClick(view: View, model: T)
    }

}