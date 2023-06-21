package dev.anmatolay.artistarchivist.feature.search.data.dto

import dev.anmatolay.artistarchivist.core.model.Artist

data class ArtistDto(
    val count: Int,
    val artists: List<Artist>,
)