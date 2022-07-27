package com.ipsoft.bibliasagrada.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ipsoft.bibliasagrada.domain.common.constants.ARG_BOOK_ID
import com.ipsoft.bibliasagrada.domain.common.constants.ARG_CHAPTER_ID
import com.ipsoft.bibliasagrada.domain.core.exception.Failure
import com.ipsoft.bibliasagrada.ui.bible.BibleViewModel
import com.ipsoft.bibliasagrada.ui.bible.books.ListBooks
import com.ipsoft.bibliasagrada.ui.bible.chapters.ListChapters
import com.ipsoft.bibliasagrada.ui.bible.reading.BibleReading
import com.ipsoft.bibliasagrada.ui.theme.BíbliaSagradaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: BibleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BíbliaSagradaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BibleApplication(viewModel)
                }
            }
        }
    }
}

@Composable
fun BibleApplication(viewModel: BibleViewModel? = null) {

    val loading: State<Boolean>? = viewModel?.loading?.observeAsState(initial = false)
    val failure: State<Failure?>? = viewModel?.failure?.observeAsState(initial = null)

    loading?.value?.let {
        if (it) {
            Loading()
        }
    }
    failure?.value


    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ListBooksScreen.route) {
        composable(route = ListBooksScreen.route) {
            ListBooks(viewModel)
        }
        composable(route = ListChaptersScreen.route, arguments = listOf(navArgument(ARG_BOOK_ID) {
            type = NavType.StringType
        })) { navBackStackEntry ->
            ListChapters(navBackStackEntry.arguments!!.getString(ARG_BOOK_ID), navController)
        }
        composable(
            route = BibleReadingScreen.route, arguments = listOf(
                navArgument(ARG_BOOK_ID) {
                    type = NavType.StringType
                },
                navArgument(ARG_CHAPTER_ID) {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            BibleReading(
                navBackStackEntry.arguments!!.getString(ARG_BOOK_ID),
                navBackStackEntry.arguments!!.getInt(ARG_CHAPTER_ID),
                navController
            )
        }
    }

}

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BíbliaSagradaTheme {
        BibleApplication()
    }
}