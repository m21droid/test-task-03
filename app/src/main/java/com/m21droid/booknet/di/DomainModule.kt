package com.m21droid.booknet.di

import com.m21droid.booknet.domain.repositories.DataRepository
import com.m21droid.booknet.domain.usecases.GetAllBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetAllBooksUseCase(dataRepository: DataRepository): GetAllBooksUseCase {
        return GetAllBooksUseCase(dataRepository)
    }

}