package dev.anmatolay.artistarchivist.core.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import dev.anmatolay.artistarchivist.R
import dev.anmatolay.artistarchivist.util.MockArtist

@Composable
fun ArtistArchivistRow(
    @StringRes titleId: Int,
    bodyContent: @Composable () -> Unit
) {
    Row(verticalAlignment = Alignment.Top) {
        TitleText(titleId, Modifier)
        bodyContent()
    }
}

@Composable
fun ArtistArchivistRow(
    @StringRes titleId: Int,
    bodyText: String,
    isShort: Boolean = true,
    isBodyItalic: Boolean = false,
    isBodyTag: Boolean = false,
) {
    Row(verticalAlignment = if (isShort) Alignment.CenterVertically else Alignment.Top) {
        TitleText(titleId, Modifier)
        BodyText(
            bodyText,
            isShort = isShort,
            fontStyle = if (isBodyItalic) FontStyle.Italic else null,
            color = if (isBodyTag) MaterialTheme.colorScheme.tertiary else Color.Unspecified,
        )
    }
}

@Preview
@Composable
fun ShortPreview() {
    ArtistArchivistRow(R.string.common_title_tags, MockArtist.withDetails.tags!!.joinToString { it.name }, isShort = true, isBodyTag = true)
}

@Preview
@Composable
fun LongPreview() {
    ArtistArchivistRow(R.string.common_title_tags, MockArtist.withDetails.tags!!.joinToString { it.name }, isShort = false, isBodyTag = true)
}
