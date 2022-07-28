package com.ipsoft.bibliasagrada.ui

import com.ipsoft.bibliasagrada.domain.common.constants.ARG_BOOK_ABBREV
import com.ipsoft.bibliasagrada.domain.common.constants.ARG_BOOK_NAME
import com.ipsoft.bibliasagrada.domain.common.constants.ARG_CHAPTER_ID

sealed class Screen(val route: String)
object ListBooksScreen : Screen("list_books")
object ListChaptersScreen :
    Screen("chapters_list/{$ARG_BOOK_NAME}/{$ARG_BOOK_ABBREV}")

object BibleReadingScreen :
    Screen("reading/{$ARG_BOOK_NAME}/{$ARG_BOOK_ABBREV}/{$ARG_CHAPTER_ID}")