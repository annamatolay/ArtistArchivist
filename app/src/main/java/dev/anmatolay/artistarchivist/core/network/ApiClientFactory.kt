package dev.anmatolay.artistarchivist.core.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.anmatolay.artistarchivist.BuildConfig
import dev.anmatolay.artistarchivist.core.network.interceptor.interceptorList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

@Single
class ApiClientFactory {

    private val logger =
        HttpLoggingInterceptor.Logger { message -> Timber.tag("Retrofit - OkHttp").d(message) }
    private val loggingInterceptor = HttpLoggingInterceptor(logger).setLevel(
        if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE

    )
    private val okHttpClientBuilder =
        OkHttpClient().newBuilder()
            .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)

    fun <T> createApiImplementation(service: Class<T>, url: String = BuildConfig.API_URL): T {
        interceptorList.forEach { okHttpClientBuilder.addInterceptor(it) }

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder()
            .client(okHttpClientBuilder.build())
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(service)
    }

    companion object {
        private const val TIMEOUT_IN_SECONDS = 60L
    }
}
