package dev.anmatolay.artistarchivist.core.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ArtistArchivistIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String = "",
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = modifier.padding(8.dp)
        )
    }
}
