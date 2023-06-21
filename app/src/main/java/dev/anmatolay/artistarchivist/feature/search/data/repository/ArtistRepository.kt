package dev.anmatolay.artistarchivist.feature.search.data.repository

import dev.anmatolay.artistarchivist.feature.search.data.api.MusicBrainzApi
import org.koin.core.annotation.Factory

@Factory
class ArtistRepository(private val api: MusicBrainzApi) {

    suspend fun getArtists(input: String) = api.service.getArtists(artistQueryValue = "artist:$input")
}