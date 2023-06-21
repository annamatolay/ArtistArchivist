package dev.anmatolay.artistarchivist.feature.search.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.anmatolay.artistarchivist.R
import dev.anmatolay.artistarchivist.core.presentation.BaseViewModel.Companion.observe
import dev.anmatolay.artistarchivist.core.presentation.TextFieldState
import dev.anmatolay.artistarchivist.core.presentation.components.ArtistArchivistFullScreen
import dev.anmatolay.artistarchivist.core.presentation.components.BodyText
import dev.anmatolay.artistarchivist.core.model.Artist
import dev.anmatolay.artistarchivist.feature.search.presentation.state.SearchTextFieldState
import dev.anmatolay.artistarchivist.feature.search.presentation.state.SearchTextFieldStateSaver
import dev.anmatolay.artistarchivist.feature.search.presentation.view.AnimatedSearchView
import dev.anmatolay.artistarchivist.feature.search.presentation.view.ResultView
import dev.anmatolay.artistarchivist.ui.theme.ArtistArchivistTheme
import dev.anmatolay.artistarchivist.ui.util.FontScalePreviews
import dev.anmatolay.artistarchivist.ui.util.UIModePreviews
import dev.anmatolay.artistarchivist.util.MockArtist
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    onItemClick: ((Artist) -> Unit)? = null,
) {
    var data: List<Artist>? by rememberSaveable { mutableStateOf(null) }
    var itemCount: Int? by rememberSaveable { mutableStateOf(null) }
    val searchState by rememberSaveable(stateSaver = SearchTextFieldStateSaver) {
        mutableStateOf(SearchTextFieldState())
    }

    SearchContent(searchState, data, itemCount, onItemClick) { input ->
        viewModel.getArtists(input)
    }

    viewModel.state.data.observe { dto ->
        itemCount = dto.count
        data = dto.artists
    }
    viewModel.state.loading.observe { searchState.isLoading = it }
    viewModel.state.error.observe { searchState.setTextFieldError(it) }
}

@Composable
fun SearchContent(
    searchState: TextFieldState = remember { SearchTextFieldState() },
    data: List<Artist>? = null,
    itemCount: Int? = null,
    onItemClick: ((Artist) -> Unit)? = null,
    onSearchAction: ((String) -> Unit)? = null,
) {
    ArtistArchivistFullScreen {
        AnimatedSearchView(searchState, onSearchAction) {
            if (data != null) {
                if (itemCount != null) {
                    BodyText(
                        text = pluralStringResource(R.plurals.search_item_count, itemCount, itemCount),
                        modifier = Modifier.padding(start = 24.dp, bottom = 8.dp),
                        textAlign = TextAlign.Start
                    )
                }
                LazyColumn(
                    modifier = Modifier.padding(vertical = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(items = data) { ResultView(it, onItemClick) }
                }
            }
        }
    }
}

@UIModePreviews
@FontScalePreviews
@Composable
fun SearchContentPreview() {
    ArtistArchivistTheme {
        SearchContent(
            data = listOf(
                MockArtist.dummy,
                MockArtist.minimal,
                MockArtist.withoutDetails,
                MockArtist.withDetails,
            ),
            itemCount = 4
        )
    }
}
