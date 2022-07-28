package com.ipsoft.bibliasagrada.ui.bible.reading

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.ipsoft.bibliasagrada.domain.model.ChapterResponse
import com.ipsoft.bibliasagrada.ui.bible.BibleViewModel

@Composable
fun BibleReading(
    bookName: String,
    bookAbbrev: String,
    chapterId: Int,
    navController: NavHostController,
    viewModel: BibleViewModel
) {

    val chapterState: State<ChapterResponse?> =
        viewModel.chapter.observeAsState(initial = null)

    viewModel.getBookChapter(bookName, bookAbbrev, chapterId)

}