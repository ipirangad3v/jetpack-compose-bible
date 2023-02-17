package com.ipsoft.bibliasagrada.domain.usecases

import com.ipsoft.bibliasagrada.data.datastore.PreferencesDataStore
import com.ipsoft.bibliasagrada.domain.core.exception.Failure
import com.ipsoft.bibliasagrada.domain.core.function.Either
import javax.inject.Inject

class DisableShowPressAndHoldVerseTutorialUseCase @Inject constructor(private val preferencesDataStore: PreferencesDataStore) :
    UseCase<Unit, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, Unit> {
        return try {
            preferencesDataStore.disableShowPressAndHoldVerseTutorial()
        } catch (ex: Exception) {
            Either.Fail(Failure.Error)
        }
    }
}
