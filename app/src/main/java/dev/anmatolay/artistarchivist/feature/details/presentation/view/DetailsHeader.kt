package dev.anmatolay.artistarchivist.feature.details.presentation.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import dev.anmatolay.artistarchivist.R
import dev.anmatolay.artistarchivist.core.presentation.components.BodyText
import dev.anmatolay.artistarchivist.core.presentation.components.HeadLineText
import dev.anmatolay.artistarchivist.core.presentation.components.TitleText
import dev.anmatolay.artistarchivist.core.model.Artist

@Composable
fun DetailsHeader(artist: Artist) {
    HeadLineText(text = artist.name, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    TitleText(text = artist.type, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    val lifeSpanText = artist.lifeSpan.begin?.let {
        stringResource(
            id = R.string.details_body_lifespan,
            it,
            artist.lifeSpan.end ?: ""
        ) + if (artist.lifeSpan.ended == true) " 🏴" else ""
    }
    lifeSpanText?.let { BodyText(text = it, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) }
}