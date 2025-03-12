package com.sample.golf.di

import com.sample.golf.data.source.remote.ApiHandler
import com.sample.golf.data.source.remote.ApiHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiHandlerModule {

    @Provides
    @Singleton
    fun provideApiHandler(): ApiHandler {
        return ApiHandlerImpl()
    }
}