package dev.anmatolay.artistarchivist.core.presentation.components

import androidx.compose.runtime.Composable
import dev.anmatolay.artistarchivist.R
import dev.anmatolay.artistarchivist.core.model.Artist
import dev.anmatolay.artistarchivist.util.extension.takeIf

@Composable
fun ArtistArchivistTag(tags: List<Artist.Tag>, isExtended: Boolean = false) {
    if (tags.isNotEmpty()) {
        if (tags.size == 1) {
            ArtistArchivistRow(R.string.common_title_tags, tags.joinToString { it.name }, isBodyTag = true)
        } else {
            val data = tags
                .sortedByDescending { it.count }
                .takeIf(6, !isExtended)
                .joinToString { it.name }

            ArtistArchivistRow(R.string.common_title_tags, data, isShort = !isExtended, isBodyTag = true)
        }
    }
}
