package com.ipsoft.bibliasagrada.di

import android.app.Application
import androidx.room.Room
import com.ipsoft.bibliasagrada.data.local.room.ChurchDao
import com.ipsoft.bibliasagrada.data.local.room.ChurchDatabase
import com.ipsoft.bibliasagrada.data.local.room.ChurchDatabase.Companion.BIBLE_DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun providesBibleDatabase(application: Application): ChurchDatabase {
        return Room
            .databaseBuilder(application, ChurchDatabase::class.java, BIBLE_DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesBibleDao(database: ChurchDatabase): ChurchDao = database.churchDao()
}
