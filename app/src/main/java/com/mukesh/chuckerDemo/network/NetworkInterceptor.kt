package com.mukesh.chuckerDemo.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.newBuilder().apply {
            header("NameRetrofit", "Yes Its Me")
        }.build()

        val response = chain.proceed(newRequest)
        handlerResponse(response)
        return response
    }

    private fun handlerResponse(response: Response) {
        Log.e("NetworkInterceptor","ResponseCode is => ${response.code}")
        if (!response.isSuccessful){
            //Show Error Message
        }
    }

}