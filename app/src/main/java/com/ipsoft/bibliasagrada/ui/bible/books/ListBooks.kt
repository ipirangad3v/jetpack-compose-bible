package com.ipsoft.bibliasagrada.ui.bible.books

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ipsoft.bibliasagrada.R
import com.ipsoft.bibliasagrada.domain.model.BookResponse
import com.ipsoft.bibliasagrada.ui.bible.BibleViewModel
import com.ipsoft.bibliasagrada.ui.components.AppBar

@Composable
fun ListBooks(viewModel: BibleViewModel? = null, navController: NavController) {

    val booksState: State<List<BookResponse>>? =
        viewModel?.books?.observeAsState(initial = emptyList())

    viewModel?.getBooks()


    Scaffold(topBar = { AppBar(stringResource(id = R.string.app_name), icon = Icons.Default.Home) }) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn {
                items(booksState?.value ?: emptyList()) { book ->
                    BookItem(book) {
                        navController.navigate("chapters_list/${book.name}/${book.abbrev.pt}/${book.chapters}")
                    }
                }
            }
        }


    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookItem(book: BookResponse, onBookClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(), onClick = onBookClick
    ) {
        Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = book.name)
            Text(text = book.abbrev.pt)

        }
    }
}



