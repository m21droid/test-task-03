package com.m21droid.booknet.presentation.main.adapters

import android.view.ViewGroup
import com.m21droid.booknet.domain.models.BookModel
import com.m21droid.booknet.presentation.base.BaseAdapter
import com.m21droid.booknet.presentation.base.BaseHolder

class BookAdapter(listener: BaseHolder.OnItemClickListener<BookModel>?) :
    BaseAdapter<BookModel>(listener) {

    override fun onCreateHolder(recyclerView: ViewGroup, viewType: Int) =
        BookHolder(recyclerView)

}