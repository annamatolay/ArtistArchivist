package dev.anmatolay.artistarchivist.feature.details.presentation.view

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import dev.anmatolay.artistarchivist.R
import dev.anmatolay.artistarchivist.core.presentation.annotatedStringBuilder
import dev.anmatolay.artistarchivist.core.presentation.components.ArtistArchivistRow
import dev.anmatolay.artistarchivist.core.presentation.components.ArtistArchivistSpacer
import dev.anmatolay.artistarchivist.core.presentation.components.ArtistArchivistTag
import dev.anmatolay.artistarchivist.core.model.Artist
import dev.anmatolay.artistarchivist.util.Contant

@Composable
fun DetailsBody(artist: Artist) {
    artist.sortName?.let { ArtistArchivistRow(titleId = R.string.common_title_name_sort, bodyText = it) }
    artist.aliases?.let { aliases ->
        ArtistArchivistRow(
            titleId = R.string.details_title_aliases,
            bodyText = aliases.joinToString { it.name })
    }
    ArtistArchivistRow(titleId = R.string.details_title_origin, bodyText = artist.origin)
    artist.isnis?.let { isnis ->
        val isniCode = isnis.first()
        val annotatedLinkString: AnnotatedString = annotatedStringBuilder(isniCode)
        val uriHandler = LocalUriHandler.current
        ArtistArchivistRow(titleId = R.string.details_title_isni) {
            ClickableText(
                text = annotatedLinkString,
                style = MaterialTheme.typography.bodyMedium,
                onClick = {
                    annotatedLinkString
                        .getStringAnnotations(Contant.URL_ANNOTATION, it, it)
                        .firstOrNull()?.let { stringAnnotation ->
                            uriHandler.openUri(stringAnnotation.item)
                        }
                }
            )
        }
    }
    ArtistArchivistSpacer(8.dp)
    artist.tags?.let { tags -> ArtistArchivistTag(tags, isExtended = true) }
}