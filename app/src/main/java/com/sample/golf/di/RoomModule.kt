package com.sample.golf.di

import android.content.Context
import androidx.room.Room
import com.sample.golf.data.source.local.room.dao.GolfDao
import com.sample.golf.data.source.local.room.db.GolfDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideGolfDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, GolfDatabase::class.java, "golf_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideGolfDao(golfDatabase: GolfDatabase): GolfDao {
        return golfDatabase.golfCourseDao()
    }
}