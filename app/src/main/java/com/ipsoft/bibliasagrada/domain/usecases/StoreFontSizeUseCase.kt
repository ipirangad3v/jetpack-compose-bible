package com.ipsoft.bibliasagrada.domain.usecases

import com.ipsoft.bibliasagrada.data.datastore.PreferencesDataStore
import com.ipsoft.bibliasagrada.domain.core.exception.Failure
import com.ipsoft.bibliasagrada.domain.core.function.Either
import javax.inject.Inject

class StoreFontSizeUseCase @Inject constructor(private val preferencesDataStore: PreferencesDataStore) :
    UseCase<Unit, StoreFontSizeUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, Unit> =
        preferencesDataStore.storeFontSize(params.fontSize)

    data class Params(val fontSize: Int)
}
