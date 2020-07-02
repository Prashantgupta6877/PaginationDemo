package com.example.paginationdemo.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiBuilder {

    private const val BASE_URL: String = "https://reqres.in/api/"

    private val gson: Gson = GsonBuilder().create()

    private val httpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)

    private val retrofitBuilder: Retrofit.Builder =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))

    fun create(): ApiService {
        retrofitBuilder.baseUrl(BASE_URL)
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        retrofitBuilder.client(httpClientBuilder.build())
        return retrofitBuilder.build().create(ApiService::class.java)
    }
}