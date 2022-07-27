package com.ipsoft.bibliasagrada.domain.usecases

import com.ipsoft.bibliasagrada.domain.core.exception.Failure
import com.ipsoft.bibliasagrada.domain.core.function.Either
import com.ipsoft.bibliasagrada.domain.model.BookResponse
import com.ipsoft.bibliasagrada.domain.model.ChapterResponse
import com.ipsoft.bibliasagrada.domain.repository.BibleRepository
import javax.inject.Inject

class GetChapterUseCase @Inject constructor(
    private val bibleRepository: BibleRepository
) : UseCase<ChapterResponse, GetChapterUseCase.Params>() {
    override suspend fun run(params: Params): Either<Failure, ChapterResponse> =
        bibleRepository.getChapter(params.book, params.selectedChapter)

    data class Params(val book: BookResponse, val selectedChapter: Int)
}
