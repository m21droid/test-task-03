package com.m21droid.booknet.data.datasources.remote

import android.util.Log
import com.m21droid.booknet.data.datasources.remote.dto.LibBookDTO
import com.m21droid.booknet.data.datasources.remote.dto.PageDTO
import com.m21droid.booknet.data.datasources.remote.rest.Const.BEARER
import com.m21droid.booknet.data.datasources.remote.rest.Const.RESPONSE_CODE
import com.m21droid.booknet.data.datasources.remote.rest.Const.SECRET
import com.m21droid.booknet.data.datasources.remote.rest.Const.TOKEN
import com.m21droid.booknet.data.datasources.remote.rest.DeviceId
import com.m21droid.booknet.data.datasources.remote.rest.RestApi
import com.m21droid.booknet.data.datasources.remote.rest.md5
import com.m21droid.booknet.domain.models.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class RemoteDataSourceImpl(
    private val restApi: RestApi,
    private val deviceId: DeviceId,
) : RemoteDataSource {

    override fun getBooks(): Flow<ResponseState<List<LibBookDTO>>> = flow {
        try {
            emit(ResponseState.Loading)

            Log.d(TAG, "getBooks: deviceId - $deviceId")
            val execute = restApi.books(
                authorization = BEARER,
                deviceId = deviceId,
                token = TOKEN,
                sign = "${deviceId}${SECRET}${TOKEN}".md5()
            ).execute()
            val code = execute.code()
            val body = execute.body() ?: listOf()
            Log.d(TAG, "getBooks: code - $code, body - $body")
            if (code == 200) {
                emit(ResponseState.Success(body))
            } else {
                emit(ResponseState.Failure(IOException(RESPONSE_CODE)))
            }
        } catch (e: IOException) {
            Log.e(TAG, "getBooks: error - ${e.message}")
            emit(ResponseState.Failure(e))
        }
    }

    override fun getBook(bookId: Int): Flow<ResponseState<List<PageDTO>>> = flow {
        try {
            emit(ResponseState.Loading)

            Log.d(TAG, "getBooks: deviceId - $deviceId")
            val execute = restApi.book(
                authorization = BEARER,
                bookId = bookId,
                deviceId = deviceId,
                token = TOKEN,
                sign = "${deviceId}${SECRET}${TOKEN}".md5()
            ).execute()
            val code = execute.code()
            val body = execute.body() ?: listOf()
            Log.d(TAG, "getBooks: code - $code, body - $body")
            if (code == 200) {
                emit(ResponseState.Success(body))
            } else {
                emit(ResponseState.Failure(IOException(RESPONSE_CODE)))
            }
        } catch (e: IOException) {
            Log.e(TAG, "getBooks: error - ${e.message}")
            emit(ResponseState.Failure(e))
        }
    }

    companion object {
        private val TAG = RemoteDataSourceImpl::class.simpleName
    }

}