package com.streamability.datalayer.utils

/***
Sealed Class wrapper to wrap any resource calls we get from our
 data layer. Helps determine if the data retrieved is a
 successful, currently loading, or Error data.
 */

sealed class Resource<T>(data: T? = null, message: String?) {
    data class Success<T> (val data: T): Resource<T>(data, null)
    class Loading<T> (): Resource<T>(null, null)
    data class Error<T> (val message: String): Resource<T>(null, message)
}
