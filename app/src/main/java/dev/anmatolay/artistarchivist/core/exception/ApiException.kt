package dev.anmatolay.artistarchivist.core.exception

import retrofit2.Response

class ApiException(response: Response<*>): Exception("${response.message()}\n${response.errorBody()}")
