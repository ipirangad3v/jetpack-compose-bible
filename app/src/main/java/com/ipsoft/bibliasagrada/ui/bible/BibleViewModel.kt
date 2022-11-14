package com.ipsoft.bibliasagrada.ui.bible

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
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
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class BibleViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val getChapterUseCase: GetChapterUseCase,
) : BaseViewModel() {


    private val _fontSize = MutableLiveData(16.sp)
    private val _currentChapter = MutableLiveData<Int>()
    private val _currentText = MutableLiveData<String>()
    private val _books: MutableLiveData<List<BookResponse>> = MutableLiveData()
    private val _lastSearch: MutableLiveData<String> = MutableLiveData("")
    private val _filteredBooks: MutableLiveData<List<BookResponse>?> = MutableLiveData()
    private val _chapter: MutableLiveData<ChapterResponse> = MutableLiveData()
    private var textToSpeech: TextToSpeech? = null
    private val _isSpeechEnabled: MutableLiveData<Boolean> = MutableLiveData(false)

    val fontSize: LiveData<TextUnit> = _fontSize
    val books: LiveData<List<BookResponse>> = _books
    val lastSearch: LiveData<String> = _lastSearch
    val filteredBooks: LiveData<List<BookResponse>?> = _filteredBooks
    val chapter: LiveData<ChapterResponse> = _chapter
    val isSpeechEnabled: LiveData<Boolean> = _isSpeechEnabled
    val currentText: LiveData<String> = _currentText
    val currentChapter: LiveData<Int> = _currentChapter

    fun setCurrentChapter(chapter: Int) {
        _currentChapter.value = chapter
    }

    fun nextChapter() {
        _currentChapter.value?.let {
            _currentChapter.value = it + 1
        }
    }

    fun previousChapter() {
        _currentChapter.value?.let {
            if (it > 1) _currentChapter.value = it - 1
        }
    }

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

    fun getBookChapter(bookName: String, bookAbbrev: String, chapterId: Int) {
        handleLoading(true)
        _chapter.postValue(null)
        return getChapterUseCase(
            GetChapterUseCase.Params(bookName, bookAbbrev, chapterId), viewModelScope
        ) {
            it.fold(
                ::handleFailure,
                ::handleFetchBookChapterSuccess
            )
        }
    }

    fun searchBook(search: String) {
        _filteredBooks.postValue(
            _books.value?.filter {
                it.name.removeAccents().contains(search, true) || it.abbrev.pt.contains(
                    search, true
                )
            }
        )
    }

    fun textToSpeech(context: Context, text: String) {
        textToSpeech = TextToSpeech(
            context
        ) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.getDefault()
                    txtToSpeech.setSpeechRate(1f)
                    txtToSpeech.speak(
                        text,
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        null
                    )
                }
            }
        }
        _isSpeechEnabled.postValue(true)

    }

    fun stopSpeech() {
        if (textToSpeech?.isSpeaking == true) {
            textToSpeech?.stop()
            textToSpeech?.shutdown()
        }
        _isSpeechEnabled.value = textToSpeech?.isSpeaking ?: false


    }

    private fun handleFetchBookChapterSuccess(chapterResponse: ChapterResponse) {
        handleLoading(false)
        _chapter.postValue(chapterResponse)
        _currentText.value = chapterResponse.verses.map { it.text + " " }
            .toString()
    }

    private fun handleBooksFetchSuccess(bookResponse: List<BookResponse>) {
        handleLoading(false)
        _books.postValue(bookResponse)
    }

    fun clearFilteredBooks() {
        _filteredBooks.postValue(null)
    }

    fun updateLastSearch(text: String) {
        _lastSearch.postValue(text)
    }
}
