package dev.anmatolay.artistarchivist.core.network.interceptor

import okhttp3.Interceptor

val interceptorList: List<Interceptor> = listOf(
    userAgentInterceptor,
)
