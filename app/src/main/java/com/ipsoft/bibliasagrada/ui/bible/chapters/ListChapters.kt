package com.ipsoft.bibliasagrada.ui.bible.chapters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ipsoft.bibliasagrada.ui.components.AppBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListChapters(
    bookName: String,
    bookAbbrev: String,
    chapterQuantity: Int,
    navController: NavHostController
) {

    Scaffold(topBar = {
        AppBar(bookName, icon = Icons.Default.ArrowBack) {
            navController.navigateUp()
        }
    }) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 70.dp)) {
                items(chapterQuantity) { index ->
                    val currentChapter = index + 1
                    ChapterItem(currentChapter) {
                        navController.navigate("reading/$bookName/$bookAbbrev/$currentChapter")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChapterItem(chapter: Int, onBookClick: () -> Unit) {

    Card(
        elevation = 5.dp,
        modifier = Modifier
            .wrapContentSize()
            .padding(12.dp),
        onClick = onBookClick
    ) {
        Text(
            text = chapter.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(12.dp)
        )
    }
}
