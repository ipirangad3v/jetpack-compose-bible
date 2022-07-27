package com.ipsoft.bibliasagrada.ui.bible

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ipsoft.bibliasagrada.domain.core.extension.removeAccents
import com.ipsoft.bibliasagrada.domain.model.BookResponse
import com.ipsoft.bibliasagrada.domain.model.ChapterResponse
import com.ipsoft.bibliasagrada.domain.usecases.GetBooksUseCase
import com.ipsoft.bibliasagrada.domain.usecases.GetChapterUseCase
import com.ipsoft.bibliasagrada.domain.usecases.UseCase
import com.ipsoft.bibliasagrada.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BibleViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val getChapterUseCase: GetChapterUseCase
) : BaseViewModel() {

    private val _books: MutableLiveData<List<BookResponse>> = MutableLiveData()
    private val _chapter: MutableLiveData<ChapterResponse> = MutableLiveData()

    val books: LiveData<List<BookResponse>> = _books
    val chapter: LiveData<ChapterResponse> = _chapter

    fun getBooks() {
        handleLoading(true)
        return getBooksUseCase(
            UseCase.None(), viewModelScope
        ) {
            it.fold(
                ::handleFailure,
                ::handleBooksFetchSuccess
            )
        }
    }

    fun searchBook(search: String) {
        _books.postValue(
            _books.value?.filter {
                it.name.removeAccents().contains(search, true) || it.abbrev.pt.contains(
                    search, true
                )
            }
        )
    }

    fun getBookChapter(book: BookResponse, selectedChapter: Int) {
        handleLoading(true)
        return getChapterUseCase(
            GetChapterUseCase.Params(book, selectedChapter), viewModelScope
        ) {
            it.fold(
                ::handleFailure,
                ::handleFetchBookChapterSuccess
            )
        }
    }

    private fun handleFetchBookChapterSuccess(chapterResponse: ChapterResponse) {
        handleLoading(false)
        _chapter.postValue(chapterResponse)
    }

    private fun handleBooksFetchSuccess(bookResponse: List<BookResponse>) {
        handleLoading(false)
        _books.postValue(bookResponse)
    }
}
