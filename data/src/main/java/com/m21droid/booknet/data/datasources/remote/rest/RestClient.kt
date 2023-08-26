package com.m21droid.booknet.data.datasources.remote.rest

import android.content.Context
import com.m21droid.booknet.data.datasources.remote.rest.Const.HTTP_CACHE
import com.m21droid.booknet.data.datasources.remote.rest.Const.MAX_SIZE
import com.m21droid.booknet.data.datasources.remote.rest.Const.TIMEOUT
import com.m21droid.data.BuildConfig.DEBUG
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit.SECONDS

class RestClient(private val context: Context) {

    val okHttpClient: OkHttpClient =
        OkHttpClient.Builder().apply {
            val file = File(context.cacheDir, HTTP_CACHE)
            val cache = Cache(file, MAX_SIZE.toLong())
            cache(cache)
            readTimeout(TIMEOUT, SECONDS)
            if (DEBUG) {
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(this)
                }
            }
        }.build()

}