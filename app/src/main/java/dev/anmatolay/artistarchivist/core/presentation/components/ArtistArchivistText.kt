package dev.anmatolay.artistarchivist.core.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun HeadLineText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
) {
    ArtistArchivistText(
        modifier = modifier,
        text = text,
        textStyle = MaterialTheme.typography.headlineMedium,
        textAlign = textAlign,
    )
}

@Composable
fun TitleText(
    @StringRes stringResId: Int,
    modifier: Modifier = Modifier,
) {
    TitleText(
        modifier = modifier,
        text = stringResource(id = stringResId),
    )
}

@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
) {
    ArtistArchivistText(
        modifier = modifier,
        text = text,
        textStyle = MaterialTheme.typography.titleMedium,
        textAlign = textAlign,
    )
}

@Composable
fun BodyText(
    @StringRes stringResId: Int,
    modifier: Modifier = Modifier,
) {
    BodyText(
        modifier = modifier,
        text = stringResource(id = stringResId),
    )
}

@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    isShort: Boolean = false,
    fontStyle: FontStyle? = null,
    fontFamily: FontFamily? = null,
    textDecoration: TextDecoration? = null,
    color: Color = Color.Unspecified,
) {
    ArtistArchivistText(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        textStyle = MaterialTheme.typography.bodyMedium,
        isShort = isShort,
        fontStyle = fontStyle,
        fontFamily = fontFamily,
        textDecoration = textDecoration,
        color = color,
    )
}

@Composable
fun ErrorText(
    @StringRes stringResId: Int,
    modifier: Modifier = Modifier,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(16.dp))
        ArtistArchivistText(
            modifier = modifier.fillMaxWidth(),
            text = stringResource(id = stringResId),
            textStyle = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun ArtistArchivistText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = LocalTextStyle.current,
    textAlign: TextAlign? = null,
    color: Color = Color.Unspecified,
    fontStyle: FontStyle? = null,
    fontFamily: FontFamily? = null,
    textDecoration: TextDecoration? = null,
    isShort: Boolean = false,
) {
    Text(
        modifier = modifier,
        text = text,
        style = textStyle,
        color = color,
        textAlign = textAlign,
        fontStyle = fontStyle,
        fontFamily = fontFamily,
        textDecoration = textDecoration,
        maxLines = if (isShort) 1 else Int.MAX_VALUE,
        overflow = if (isShort) TextOverflow.Ellipsis else TextOverflow.Clip,
    )
}