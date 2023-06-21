package dev.anmatolay.artistarchivist.core.network.interceptor

import dev.anmatolay.artistarchivist.BuildConfig
import okhttp3.Interceptor

val userAgentInterceptor = Interceptor { chain ->

    // Header based on MusicBrainz API best practices
    // https://musicbrainz.org/doc/MusicBrainz_API/Rate_Limiting#Provide_meaningful_User-Agent_strings
    val request = chain.request().newBuilder()
        .header(
            "User-Agent",
        "${BuildConfig.APP_NAME}/${BuildConfig.VERSION_NAME} ( ${BuildConfig.CONTACT_EMAIL} )"
        )
        .build()

    chain.proceed(request)
}
