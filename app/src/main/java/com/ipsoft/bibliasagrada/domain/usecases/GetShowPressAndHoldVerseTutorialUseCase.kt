package com.ipsoft.bibliasagrada.domain.usecases

import com.ipsoft.bibliasagrada.data.datastore.PreferencesDataStore
import com.ipsoft.bibliasagrada.domain.core.exception.Failure
import com.ipsoft.bibliasagrada.domain.core.function.Either
import javax.inject.Inject

class GetShowPressAndHoldVerseTutorialUseCase @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore,
) :
    UseCase<Boolean, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, Boolean> =
        preferencesDataStore.readShowPressAndHoldVerseTutorial()
}
