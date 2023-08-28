package com.m21droid.booknet.domain.repositories

import com.m21droid.booknet.domain.models.BookModel
import com.m21droid.booknet.domain.models.PageModel
import com.m21droid.booknet.domain.models.ResponseState
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    fun getAllBooks(): Flow<ResponseState<List<BookModel>>>

    fun getBook(bookId: Int): Flow<ResponseState<List<PageModel>>>

}