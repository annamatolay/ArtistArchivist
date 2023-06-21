package dev.anmatolay.artistarchivist.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import dev.anmatolay.artistarchivist.R
import dev.anmatolay.artistarchivist.feature.details.presentation.DetailsScreen
import dev.anmatolay.artistarchivist.core.model.Artist
import dev.anmatolay.artistarchivist.feature.search.presentation.SearchScreen
import dev.anmatolay.artistarchivist.ui.theme.ArtistArchivistTheme
import dev.anmatolay.artistarchivist.ui.util.FontScalePreviews
import dev.anmatolay.artistarchivist.ui.util.UIModePreviews

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtistArchivistApp()
        }
    }
}

@Composable
fun ArtistArchivistApp() {
    val context = LocalContext.current

    var isDetailsVisible: Boolean by rememberSaveable { mutableStateOf(false) }
    var artist: Artist? by rememberSaveable { mutableStateOf(null) }

    ArtistArchivistTheme {
        SearchScreen {
            isDetailsVisible = true
            artist = it
        }

        if (isDetailsVisible) {
            artist?.let { DetailsScreen(it, onCloseClick = { isDetailsVisible = false }) } ?: Toast.makeText(
                context,
                stringResource(id = R.string.details_error_defailt),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@UIModePreviews
@FontScalePreviews
@Composable
fun Preview() {
    // Koin injection not working with Compose Preview
    // IllegalStateException: KoinApplication has not been started
    // More info:
    // https://github.com/InsertKoinIO/koin/issues/1072
    // https://github.com/InsertKoinIO/koin/issues/1119
    ArtistArchivistApp()
}
