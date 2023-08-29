package com.m21droid.booknet.data.datasources.remote.rest

import android.content.Context
import android.os.Build
import com.m21droid.booknet.data.datasources.remote.rest.Const.HTTP_CACHE
import com.m21droid.booknet.data.datasources.remote.rest.Const.MAX_SIZE
import com.m21droid.booknet.data.datasources.remote.rest.Const.TIMEOUT
import com.m21droid.data.BuildConfig.DEBUG
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit.SECONDS
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

class RestClient(private val context: Context) {

    val okHttpClient: OkHttpClient =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            OkHttpClient.Builder()
        } else {
            try {
                val trustManager = object : X509TrustManager {
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>, authType: String,
                    ) {
                    }

                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>, authType: String,
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                }
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, arrayOf(trustManager), SecureRandom())
                val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
                OkHttpClient.Builder().apply {
                    sslSocketFactory(sslSocketFactory, trustManager)
                    hostnameVerifier { _, _ ->
                        true
                    }
                }
            } catch (e: Exception) {
                OkHttpClient.Builder()
            }
        }.apply {
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