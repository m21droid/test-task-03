package com.m21droid.booknet.data.datasources.remote

import com.m21droid.booknet.data.datasources.remote.dto.LibBookDTO
import com.m21droid.booknet.domain.models.ResponseState
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getBooks(): Flow<ResponseState<List<LibBookDTO>>>

}