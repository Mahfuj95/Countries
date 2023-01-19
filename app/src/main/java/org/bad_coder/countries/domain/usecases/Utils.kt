package org.bad_coder.countries.domain.usecases

import org.bad_coder.countries.data.Resource
import retrofit2.HttpException
import java.io.IOException

suspend fun <T, K> handleNetworkCall(
    action: suspend () -> T,
    onSuccess: (T?) -> Resource<K>
): Resource<K> {
    val result = try {
        action()
    } catch (e: Exception) {
        when (e) {
            is IOException -> {
                return Resource.Error("Couldn't reach server. Please check your internet connection")
            }
            is HttpException -> {
                return Resource.Error("HttpException: Unknown response")
            }
            else -> {
                return Resource.Error("Unknown error happened: ${e.message}")
            }
        }
    }

    return onSuccess(result)
}