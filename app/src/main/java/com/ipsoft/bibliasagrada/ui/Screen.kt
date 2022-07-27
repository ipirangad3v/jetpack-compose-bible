package com.ipsoft.bibliasagrada.ui

import com.ipsoft.bibliasagrada.domain.common.constants.ARG_BOOK_ID
import com.ipsoft.bibliasagrada.domain.common.constants.ARG_CHAPTER_ID

sealed class Screen(val route: String)
object ListBooksScreen : Screen("list_books")
object ListChaptersScreen : Screen("chapters_list/{$ARG_CHAPTER_ID}")
object BibleReadingScreen : Screen("reading/{$ARG_BOOK_ID}/{$ARG_CHAPTER_ID}")