package com.ipsoft.bibliasagrada.ui.bible.books

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ipsoft.bibliasagrada.R
import com.ipsoft.bibliasagrada.domain.model.BookResponse
import com.ipsoft.bibliasagrada.ui.bible.BibleViewModel
import com.ipsoft.bibliasagrada.ui.components.AppBar

@Composable
fun ListBooks(viewModel: BibleViewModel? = null, navController: NavController) {

    val textState = remember { mutableStateOf(TextFieldValue("")) }

    val booksState: State<List<BookResponse>>? =
        viewModel?.books?.observeAsState(initial = emptyList())
    val filteredBooksState: State<List<BookResponse>?>? = viewModel?.filteredBooks?.observeAsState(
        initial = null)

    viewModel?.getBooks()


    Scaffold(topBar = {
        AppBar(
            stringResource(id = R.string.app_name),
            icon = Icons.Default.Home
        )
    }) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column() {
                SearchView(textState, viewModel)
                LazyColumn {
                    items(filteredBooksState?.value ?: booksState?.value ?: emptyList()) { book ->
                        BookItem(book) {
                            navController.navigate("chapters_list/${book.name}/${book.abbrev.pt}/${book.chapters}")
                        }
                    }
                }
            }
        }


    }
}

@Composable
fun SearchView(state: MutableState<TextFieldValue>, viewModel: BibleViewModel?) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = state.value,
            onValueChange = { value: TextFieldValue ->
                state.value = value
                viewModel?.searchBook(value.text)
            },
            label = { Text(stringResource(id = R.string.find_book)) },
            singleLine = true
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookItem(book: BookResponse, onBookClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(), onClick = onBookClick
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = book.name)
            Text(text = book.abbrev.pt)

        }
    }
}



