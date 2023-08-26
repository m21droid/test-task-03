package com.m21droid.booknet.di

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings.Secure.*
import com.m21droid.booknet.data.datasources.remote.RemoteDataSource
import com.m21droid.booknet.data.datasources.remote.RemoteDataSourceImpl
import com.m21droid.booknet.data.datasources.remote.rest.DeviceId
import com.m21droid.booknet.data.datasources.remote.rest.RestApi
import com.m21droid.booknet.data.datasources.remote.rest.RestClient
import com.m21droid.booknet.data.datasources.remote.rest.RestModule
import com.m21droid.booknet.data.repositories.DataRepositoryImpl
import com.m21droid.booknet.domain.repositories.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataRemoteModule {

    @Singleton
    @Provides
    fun provideRestClient(@ApplicationContext context: Context): OkHttpClient =
        RestClient(context).okHttpClient

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        RestModule(okHttpClient).retrofit

    @Singleton
    @Provides
    fun provideRestApi(retrofit: Retrofit): RestApi =
        retrofit.create(RestApi::class.java)

    @SuppressLint("HardwareIds")
    @Singleton
    @Provides
    fun provideDeviceId(@ApplicationContext context: Context): DeviceId =
        getString(context.contentResolver, ANDROID_ID)


    @Provides
    @Singleton
    fun provideRemoteDataSource(restApi: RestApi, deviceId: DeviceId): RemoteDataSource {
        return RemoteDataSourceImpl(restApi, deviceId)
    }

    @Provides
    @Singleton
    fun provideDataRepository(remoteDataSource: RemoteDataSource): DataRepository {
        return DataRepositoryImpl(remoteDataSource)
    }

}