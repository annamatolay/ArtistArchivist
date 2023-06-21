package dev.anmatolay.artistarchivist.core.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.anmatolay.artistarchivist.ui.theme.foundationLogoFamily

@Composable
fun ArtistArchivistOutlinedButton(
    @StringRes textIdRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    ArtistArchivistOutlinedButton(stringResource(id = textIdRes), modifier = modifier) { onClick() }
}

@Composable
fun ArtistArchivistOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    OutlinedButton(modifier = modifier, shape = RoundedCornerShape(12.dp), onClick = { onClick() }) {
        BodyText(text = text, fontFamily = foundationLogoFamily)
    }
}
