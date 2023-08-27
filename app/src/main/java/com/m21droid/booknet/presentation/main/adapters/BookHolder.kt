package com.m21droid.booknet.presentation.main.adapters

import android.view.ViewGroup
import com.m21droid.booknet.R
import com.m21droid.booknet.databinding.ItemBookBinding
import com.m21droid.booknet.domain.models.BookModel
import com.m21droid.booknet.logD
import com.m21droid.booknet.presentation.base.BaseHolder

class BookHolder(recyclerView: ViewGroup) :
    BaseHolder<BookModel>(recyclerView, R.layout.item_book, null) {

    override fun bindItem(model: BookModel) {
        logD("bindItem: model - $model")

        ItemBookBinding.bind(itemView).apply {
            model.apply {
                textViewItemTitle.text = title
                textViewItemAuthor.text = authorName
                textViewItemGenre.text = genres
            }
        }
    }

}