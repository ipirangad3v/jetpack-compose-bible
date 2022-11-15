package com.ipsoft.bibliasagrada.di

import com.ipsoft.bibliasagrada.data.datastore.PreferencesDataStore
import com.ipsoft.bibliasagrada.domain.repository.BibleRepository
import com.ipsoft.bibliasagrada.domain.usecases.DisableShowPressAndHoldVerseTutorialUseCase
import com.ipsoft.bibliasagrada.domain.usecases.GetBooksUseCase
import com.ipsoft.bibliasagrada.domain.usecases.GetChapterUseCase
import com.ipsoft.bibliasagrada.domain.usecases.GetFontSizeUseCase
import com.ipsoft.bibliasagrada.domain.usecases.GetShowPressAndHoldVerseTutorialUseCase
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
    fun createGetBooksUseCase(repository: BibleRepository): GetBooksUseCase =
        GetBooksUseCase(repository)

    @Provides
    @Singleton
    fun createGetChaptersUseCase(repository: BibleRepository): GetChapterUseCase =
        GetChapterUseCase(repository)

    @Provides
    @Singleton
    fun createGetFontSizeUseCase(preferencesDataStore: PreferencesDataStore): GetFontSizeUseCase =
        GetFontSizeUseCase(preferencesDataStore)

    @Provides
    @Singleton
    fun createDisableShowPressAndHoldVerseTutorialUseCase(
        preferencesDataStore: PreferencesDataStore,
    ): DisableShowPressAndHoldVerseTutorialUseCase =
        DisableShowPressAndHoldVerseTutorialUseCase(preferencesDataStore)

    @Provides
    @Singleton
    fun createGetShowPressAndHoldVerseTutorialUseCase(
        preferencesDataStore: PreferencesDataStore,
    ): GetShowPressAndHoldVerseTutorialUseCase =
        GetShowPressAndHoldVerseTutorialUseCase(preferencesDataStore)
}
