package com.mukesh.chuckerDemo.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mukesh.chuckerDemo.network.NetworkStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import retrofit2.Response

/**
 * Observe Data
 * */
fun <T> LiveData<NetworkStatus<T>>.observeData(
    lifecycleOwner: LifecycleOwner,
    onLoading: () -> Unit = {},
    onSuccess: (data: T) -> Unit,
    onError: (error: String) -> Unit = {}
) {
    observe(lifecycleOwner) {
        when (it) {
            is NetworkStatus.Loading -> {
                onLoading()
            }

            is NetworkStatus.Error -> {
                onError(it.error)
                //Toast Add
            }

            is NetworkStatus.Success -> {
                onSuccess(it.data)
            }
        }
    }
}


suspend fun <T> Flow<Response<T>>.setData(mutableLiveData: MutableLiveData<NetworkStatus<T?>>) {
    onStart {
        mutableLiveData.postValue(NetworkStatus.Loading())
    }.catch {
        mutableLiveData.postValue(NetworkStatus.Error(it.localizedMessage.toString()))
    }.collectLatest {
        if (it.isSuccessful) {
            mutableLiveData.postValue(NetworkStatus.Success(it.body()))
        } else {
            mutableLiveData.postValue(NetworkStatus.Error(it.errorBody()?.string().toString()))
        }
    }
}
