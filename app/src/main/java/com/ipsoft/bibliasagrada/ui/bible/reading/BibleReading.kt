package com.ipsoft.bibliasagrada.ui.bible.reading

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ipsoft.bibliasagrada.R
import com.ipsoft.bibliasagrada.domain.model.ChapterResponse
import com.ipsoft.bibliasagrada.domain.model.Verse
import com.ipsoft.bibliasagrada.ui.bible.BibleViewModel
import com.ipsoft.bibliasagrada.ui.components.AppBar
import com.ipsoft.bibliasagrada.ui.components.ErrorScreen
import com.ipsoft.bibliasagrada.ui.components.Loading

@Composable
fun BibleReading(
    bookName: String,
    bookAbbrev: String,
    chapterId: Int,
    navController: NavHostController,
    viewModel: BibleViewModel,
    loading: State<Boolean>,
) {

    val shouldGetBook by rememberUpdatedState(newValue = true)

    val chapterState: State<ChapterResponse?> =
        viewModel.chapter.observeAsState(initial = null)
    val isSpeechEnable: State<Boolean> =
        viewModel.isSpeechEnabled.observeAsState(initial = false)
    val currentText: State<String> = viewModel.currentText.observeAsState(initial = "")

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
                viewModel.stopSpeech()
                navController.navigateUp()
            }
        },
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            if (loading.value) Loading()
            if (chapterState.value == null && !loading.value) ErrorScreen {
                viewModel.getBookChapter(
                    bookName,
                    bookAbbrev,
                    chapterId
                )
            }
            LazyColumn {
                items(chapterState.value?.verses ?: emptyList()) { verse ->
                    VerseItem(verse)
                }
                item { Spacer(modifier = Modifier.height(48.dp)) }
            }
            BottomMenu(
                viewModel,
                isSpeechEnable,
                currentText
            )
        }
    }
}

@Composable
fun BottomMenu(
    viewModel: BibleViewModel,
    isSpeechEnable: State<Boolean>,
    currentText: State<String>,
) {

    val context = LocalContext.current

    Card(elevation = 8.dp, modifier = Modifier
        .padding(8.dp)
        .wrapContentSize(align = Alignment.BottomCenter)
        .wrapContentSize()) {
        Row(verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(8.dp)) {
            Row(modifier = Modifier.clickable {
                if (!isSpeechEnable.value) viewModel.textToSpeech(context,
                    currentText.value) else viewModel.stopSpeech()
            }) {
                Icon(imageVector = if (isSpeechEnable.value) Icons.Filled.Clear else Icons.Filled.PlayArrow,
                    contentDescription = null)
                Spacer(modifier = Modifier.width(2.dp))
                Text(text = if (isSpeechEnable.value) stringResource(id = R.string.stop_speech) else stringResource(
                    id = R.string.speech))
            }
//                Icon(imageVector = Icons.Default.Star, contentDescription = null)
//                Icon(imageVector = Icons.Default.Share, contentDescription = null)
        }
    }
}


@Composable
fun VerseItem(verse: Verse, onClick: (() -> Unit)? = null) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                if (onClick != null) {
                    onClick()
                }
            }
    ) {
        Text(text = "${verse.number}. ${verse.text}")
    }
}
