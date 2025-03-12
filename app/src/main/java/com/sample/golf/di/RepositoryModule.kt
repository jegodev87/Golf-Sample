package com.sample.golf.di

import com.sample.golf.data.repository.GolfDatabaseRepositoryImpl
import com.sample.golf.data.repository.GolfNetworkRepositoryImpl
import com.sample.golf.data.source.local.room.dao.GolfDao
import com.sample.golf.data.source.remote.ApiHandler
import com.sample.golf.data.source.remote.clients.GolfClient
import com.sample.golf.domain.repository.GolfDatabaseRepository
import com.sample.golf.domain.repository.GolfNetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGolfRemoteRepo(golfClient: GolfClient, apiHandler: ApiHandler): GolfNetworkRepository {
        return GolfNetworkRepositoryImpl(apiHandler = apiHandler, golfClient =  golfClient)
    }

    @Singleton
    @Provides
    fun provideGolfDatabaseRepo(golfDao: GolfDao): GolfDatabaseRepository {
        return GolfDatabaseRepositoryImpl(golfDao)
    }


}