package com.ipsoft.bibliasagrada.domain.repository

import com.ipsoft.bibliasagrada.domain.core.exception.Failure
import com.ipsoft.bibliasagrada.domain.core.function.Either
import retrofit2.Call
import timber.log.Timber

interface Repository {

    fun <T, R> request(
        call: Call<T>,
        transform: (T) -> R,
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            val either = when (response.isSuccessful) {
                true -> Either.Success(transform((response.body()!!)))
                false -> Either.Fail(Failure.ServerError)
            }
            either
        } catch (exception: Throwable) {
            Timber.e(exception)
            Either.Fail(Failure.ServerError)
        }
    }
}
