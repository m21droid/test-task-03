package com.m21droid.booknet.data.datasources.remote.rest

import com.m21droid.booknet.data.datasources.remote.dto.LibBookDTO
import com.m21droid.booknet.data.datasources.remote.dto.PageDTO
import com.m21droid.booknet.data.datasources.remote.rest.Const.AUTHORIZATION
import com.m21droid.booknet.data.datasources.remote.rest.Const.QUERY_APP
import com.m21droid.booknet.data.datasources.remote.rest.Const.QUERY_DEBUG
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {

    @GET("library/get")
    fun books(
        @Header(AUTHORIZATION) authorization: String,
        @Query("app") app: String = QUERY_APP,
        @Query("device_id") deviceId: DeviceId,
        @Query("user_token") token: String,
        @Query("sign") sign: String,
        @Query("version") version: String = QUERY_DEBUG,
    ): Call<List<LibBookDTO>>

    @GET("book/get-text/{bookId}")
    fun book(
        @Header(AUTHORIZATION) authorization: String,
        @Path("bookId") bookId: Int,
        @Query("app") app: String = QUERY_APP,
        @Query("device_id") deviceId: DeviceId,
        @Query("user_token") token: String,
        @Query("sign") sign: String,
        @Query("version") version: String = QUERY_DEBUG,
    ): Call<List<PageDTO>>

}