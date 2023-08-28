package com.m21droid.booknet.data.repositories

import android.util.Log
import com.m21droid.booknet.data.datasources.remote.RemoteDataSource
import com.m21droid.booknet.data.datasources.remote.mappers.toPageModel
import com.m21droid.booknet.domain.models.BookModel
import com.m21droid.booknet.domain.models.PageModel
import com.m21droid.booknet.domain.models.ResponseState
import com.m21droid.booknet.domain.repositories.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataRepositoryImpl(private val remoteDataSource: RemoteDataSource) : DataRepository {

    override fun getAllBooks(): Flow<ResponseState<List<BookModel>>> {
        Log.i(TAG, "getAllBooks: ")

        return remoteDataSource.getBooks().map { state ->
            when (state) {
                ResponseState.Loading -> {
                    ResponseState.Loading
                }

                is ResponseState.Failure -> {
                    ResponseState.Failure(state.throwable)
                }

                is ResponseState.Success -> {
                    val list = state.data.map {
                        it.toPageModel()
                    }
                    ResponseState.Success(list)
                }
            }
        }
    }

    override fun getBook(bookId: Int): Flow<ResponseState<List<PageModel>>> {
        Log.i(TAG, "getBook: bookId - $bookId")

        return remoteDataSource.getBook(bookId).map { state ->
            when (state) {
                ResponseState.Loading -> {
                    ResponseState.Loading
                }

                is ResponseState.Failure -> {
                    ResponseState.Failure(state.throwable)
                }

                is ResponseState.Success -> {
                    val list = state.data.map {
                        it.toPageModel()
                    }
                    ResponseState.Success(list)
                }
            }
        }
    }

    companion object {
        private val TAG = DataRepositoryImpl::class.simpleName
    }

}