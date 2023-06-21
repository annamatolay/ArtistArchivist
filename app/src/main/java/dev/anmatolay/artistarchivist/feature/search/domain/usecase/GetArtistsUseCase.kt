package dev.anmatolay.artistarchivist.domain.usecase

import dev.anmatolay.artistarchivist.feature.search.data.repository.ArtistRepository
import org.koin.core.annotation.Factory

@Factory
class GetArtistsUseCase(private val repository: ArtistRepository) {

    suspend operator fun invoke(input: String) = repository.getArtists(input)
}