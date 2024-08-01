package com.mukesh.chuckerDemo.network

sealed class NetworkStatus<T> {
    class Loading<T>() : NetworkStatus<T>()

    class Success<T>(val data: T): NetworkStatus<T>()

    class Error<T>(val error: String): NetworkStatus<T>()
}
