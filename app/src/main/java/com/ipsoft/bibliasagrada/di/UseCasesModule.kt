package com.ipsoft.bibliasagrada.di

import com.ipsoft.bibliasagrada.domain.repository.BibleRepository
import com.ipsoft.bibliasagrada.domain.usecases.GetBooksUseCase
import com.ipsoft.bibliasagrada.domain.usecases.GetChapterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    @Singleton
    fun createGetBooksUseCase(repository: BibleRepository): GetBooksUseCase {
        return GetBooksUseCase(repository)
    }

    @Provides
    @Singleton
    fun createGetChaptersUseCase(repository: BibleRepository): GetChapterUseCase {
        return GetChapterUseCase(repository)
    }

}
