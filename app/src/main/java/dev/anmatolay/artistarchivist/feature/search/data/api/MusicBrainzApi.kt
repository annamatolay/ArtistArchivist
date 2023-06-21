package dev.anmatolay.artistarchivist.feature.search.data.api

import dev.anmatolay.artistarchivist.core.network.ApiClientFactory
import dev.anmatolay.artistarchivist.feature.search.data.dto.ArtistDto
import org.koin.core.annotation.Factory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

@Factory
class MusicBrainzApi(apiClientFactory: ApiClientFactory) {

    var service = apiClientFactory.createApiImplementation(MusicBrainzService::class.java)

    interface MusicBrainzService {

        @GET("artist/")
        suspend fun getArtists(
            @Query("query") artistQueryValue: String,
            @Query("fmt") format: String = "json",
        ): Response<ArtistDto>
    }
}
