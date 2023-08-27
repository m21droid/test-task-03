package com.m21droid.booknet.presentation.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Base class representing [RecyclerView.Adapter]
 *
 * @param <T> Class-model that representing adapter item
</T> */

abstract class BaseAdapter<T : Any>(private val listener: BaseHolder.OnItemClickListener<T>? = null) :
    RecyclerView.Adapter<BaseHolder<T>>() {

    private val items: MutableList<T> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        onCreateHolder(parent, viewType).apply {
            setOnItemClickListener(listener)
        }

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) {
        holder.bindModel(items[position])
    }

    override fun getItemCount() = items.size

    fun isEmpty() = items.isEmpty()

    fun addItems(newItems: MutableList<T?>) {
        val positionStart = items.size
        val isAdded = items.addAll(newItems.filterNotNull())
        if (isAdded) {
            notifyItemRangeInserted(positionStart, newItems.size)
        }
    }

    fun changeItems(newItems: MutableList<T?>) {
        items.clear()
        items.addAll(newItems.filterNotNull())
    }

    fun removeItem(item: T) {
        val removedIndex = items.indexOf(item)
        if (removedIndex >= 0) {
            items.removeAt(removedIndex)
            notifyItemRemoved(removedIndex)
        }
    }

    fun clearItems() {
        val itemCount = items.size
        items.clear()
        notifyItemRangeRemoved(0, itemCount)
    }

    fun notifyItemChanged(model: T) {
        val index = items.indexOf(model)
        if (index >= 0) {
            notifyItemChanged(index)
        }
    }

    fun notifyItemAllChanged() {
        notifyItemRangeChanged(0, itemCount)
    }

    abstract fun onCreateHolder(recyclerView: ViewGroup, viewType: Int): BaseHolder<T>

}