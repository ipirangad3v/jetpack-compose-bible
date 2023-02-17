package com.ipsoft.bibliasagrada.domain.usecases

import com.ipsoft.bibliasagrada.domain.core.exception.Failure
import com.ipsoft.bibliasagrada.domain.core.function.Either
import com.ipsoft.bibliasagrada.domain.model.BookResponse
import com.ipsoft.bibliasagrada.domain.repository.BibleRepository
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val bibleRepository: BibleRepository,
) : UseCase<List<BookResponse>, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, List<BookResponse>> {
        return try {
            bibleRepository.getBooks()
        } catch (ex: Exception) {
            Either.Fail(Failure.Error)
        }
    }
}
