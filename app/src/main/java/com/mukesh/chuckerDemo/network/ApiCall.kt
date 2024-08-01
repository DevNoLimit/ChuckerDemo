package com.mukesh.chuckerDemo.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mukesh.chuckerDemo.MyApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiCall {

    //Gson Converter
    private fun getGsonConverter() : GsonConverterFactory = GsonConverterFactory.create()


    //Coroutine Factory
    private fun getCoroutineFactory() = CoroutineCallAdapterFactory()



    //Ok Http
    private fun getOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30 , TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(NetworkInterceptor())
        .addInterceptor(ChuckerInterceptor(MyApplication.context?.get()!!))
        .build()



    //Retrofit
    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(getOkHttpClient())
        .addConverterFactory(getGsonConverter())
        .addCallAdapterFactory(getCoroutineFactory())
        .build()


    //Make Call
    fun makeCall(): ApiInterface = getRetrofit().create(ApiInterface::class.java)

}