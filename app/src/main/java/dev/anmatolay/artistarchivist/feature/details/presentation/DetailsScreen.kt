package dev.anmatolay.artistarchivist.feature.details.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.anmatolay.artistarchivist.R
import dev.anmatolay.artistarchivist.feature.details.presentation.view.DetailsBody
import dev.anmatolay.artistarchivist.feature.details.presentation.view.DetailsHeader
import dev.anmatolay.artistarchivist.core.presentation.components.ArtistArchivistCard
import dev.anmatolay.artistarchivist.core.presentation.components.ArtistArchivistFullScreen
import dev.anmatolay.artistarchivist.core.presentation.components.ArtistArchivistOutlinedButton
import dev.anmatolay.artistarchivist.core.presentation.components.ArtistArchivistSpacer
import dev.anmatolay.artistarchivist.core.model.Artist
import dev.anmatolay.artistarchivist.ui.theme.ArtistArchivistTheme
import dev.anmatolay.artistarchivist.ui.util.FontScalePreviews
import dev.anmatolay.artistarchivist.ui.util.UIModePreviews
import dev.anmatolay.artistarchivist.util.MockArtist

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DetailsScreen(
    artist: Artist = MockArtist.dummy,
    isDetailsVisible: Boolean = true,
    onCloseClick: () -> Unit = {},
) {
    AnimatedVisibility(visible = isDetailsVisible, enter = fadeIn(), exit = fadeOut()) {
        ArtistArchivistFullScreen(isTransparent = true) {
            AnimatedVisibility(visible = isDetailsVisible, enter = scaleIn(), exit = scaleOut()) {
                Column(verticalArrangement = Arrangement.Center) {
                    DetailsContent(artist, onCloseClick)
                }
            }
        }
    }
}

@Composable
fun DetailsContent(
    artist: Artist = MockArtist.dummy,
    onCloseClick: () -> Unit = {},
) {
    ArtistArchivistCard {
        DetailsHeader(artist = artist)

        ArtistArchivistSpacer(16.dp)

        DetailsBody(artist)

        ArtistArchivistSpacer(24.dp)

        ArtistArchivistOutlinedButton(R.string.details_button_close, Modifier.fillMaxWidth()) { onCloseClick() }
    }
}

@UIModePreviews
@FontScalePreviews
@Composable
fun DetailsScreenPreview() {
    ArtistArchivistTheme {
        DetailsScreen(
            artist = MockArtist.withDetails,
        )
    }
}