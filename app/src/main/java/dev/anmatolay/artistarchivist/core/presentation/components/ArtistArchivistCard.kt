package dev.anmatolay.artistarchivist.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ArtistArchivistElevatedCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            content()
        }
    }
}

@Composable
fun ArtistArchivistCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Card(
        shape = RoundedCornerShape(21.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 9.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
        ) {
            content()
        }
    }
}