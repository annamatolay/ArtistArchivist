package dev.anmatolay.artistarchivist.feature.search.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.anmatolay.artistarchivist.R
import dev.anmatolay.artistarchivist.core.presentation.components.ArtistArchivistElevatedCard
import dev.anmatolay.artistarchivist.core.presentation.components.ArtistArchivistRow
import dev.anmatolay.artistarchivist.core.presentation.components.ArtistArchivistTag
import dev.anmatolay.artistarchivist.core.presentation.components.BodyText
import dev.anmatolay.artistarchivist.core.model.Artist
import dev.anmatolay.artistarchivist.ui.theme.ArtistArchivistTheme
import dev.anmatolay.artistarchivist.util.MockArtist

@Composable
internal fun ResultView(artist: Artist, onItemClick: ((Artist) -> Unit)? = null) {
    ArtistArchivistElevatedCard(Modifier.clickable { onItemClick?.invoke(artist) }) {
        ArtistArchivistRow(R.string.result_title_name, artist.name)
        artist.sortName?.let { ArtistArchivistRow(R.string.common_title_name_sort, it) }
        ArtistArchivistRow(R.string.result_title_type, artist.type)
        Row {
            ArtistArchivistRow(R.string.result_title_gender, artist.gender)
            BodyText(
                modifier = Modifier.padding(start = 4.dp),
                text = artist.gender.genderSymbol
            )
        }
        artist.country?.let {
            Row {
                ArtistArchivistRow(R.string.result_title_country, it)
                BodyText(text = it.flagEmoji, Modifier.padding(start = 4.dp))
            }
        }
        artist.disambiguation?.let { ArtistArchivistRow(R.string.result_title_disambiguation, it, isBodyItalic = true) }
        artist.tags?.let { tags -> ArtistArchivistTag(tags) }
    }
}

private val String.genderSymbol: String
    get() {
        return when (this) {
            "male" -> "♂"
            "female" -> "♀"
            "non-binary" -> "☿️"
            else -> ""
        }
    }
private val String.flagEmoji: String
    get() {
        val firstLetter = getCharacterCode(0)
        val secondLetter = getCharacterCode(1)
        return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
    }

private fun String.getCharacterCode(index: Int) = Character.codePointAt(this, index) - 0x41 + 0x1F1E6

@Preview
@Composable
fun ResultViewPreview() {
    ArtistArchivistTheme { ResultView(MockArtist.withDetails) }
}
