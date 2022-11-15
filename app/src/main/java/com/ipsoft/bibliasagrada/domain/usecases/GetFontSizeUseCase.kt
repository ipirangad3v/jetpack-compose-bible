package com.ipsoft.bibliasagrada.domain.usecases

import com.ipsoft.bibliasagrada.data.datastore.PreferencesDataStore
import com.ipsoft.bibliasagrada.domain.core.exception.Failure
import com.ipsoft.bibliasagrada.domain.core.function.Either
import javax.inject.Inject

class GetFontSizeUseCase @Inject constructor(private val preferencesDataStore: PreferencesDataStore) :
    UseCase<Int, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, Int> =
        preferencesDataStore.readFontSize()
}
