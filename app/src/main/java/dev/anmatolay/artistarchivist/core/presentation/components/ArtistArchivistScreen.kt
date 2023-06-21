package dev.anmatolay.artistarchivist.core.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ArtistArchivistFullScreen(isTransparent: Boolean = false, content: @Composable () -> Unit) {
    ArtistArchivistScreen(modifier = Modifier.fillMaxSize(), isTransparent) {
        content()
    }
}

@Composable
private fun ArtistArchivistScreen(
    modifier: Modifier = Modifier,
    isTransparent: Boolean,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        color = if (isTransparent)
            Color.Transparent.copy(alpha = 0.6f)
        else
            MaterialTheme.colorScheme.background
    ) {
        content()
    }
}
