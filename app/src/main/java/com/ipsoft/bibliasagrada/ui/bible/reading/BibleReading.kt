package com.ipsoft.bibliasagrada.ui.bible.reading

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ipsoft.bibliasagrada.R
import com.ipsoft.bibliasagrada.domain.model.ChapterResponse
import com.ipsoft.bibliasagrada.domain.model.Verse
import com.ipsoft.bibliasagrada.ui.bible.BibleViewModel
import com.ipsoft.bibliasagrada.ui.components.AppBar

@Composable
fun BibleReading(
    bookName: String,
    bookAbbrev: String,
    chapterId: Int,
    navController: NavHostController,
    viewModel: BibleViewModel,
) {

    val shouldGetBook by rememberUpdatedState(newValue = true)

    val chapterState: State<ChapterResponse?> =
        viewModel.chapter.observeAsState(initial = null)

    LaunchedEffect(true) {
        if (shouldGetBook) {
            viewModel.getBookChapter(bookName, bookAbbrev, chapterId)
        }


    }
    Scaffold(
        topBar = {
            AppBar(
                title = "$bookName - ${stringResource(id = R.string.chapter)} $chapterId",
                icon = Icons.Default.ArrowBack
            ) {
                navController.navigateUp()
            }
        },
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn {
                items(chapterState.value?.verses ?: emptyList()) { verse ->
                    VerseItem(verse)
                }
            }
        }


    }

}

@Composable
fun VerseItem(verse: Verse) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "${verse.number}. ${verse.text}")
    }
}