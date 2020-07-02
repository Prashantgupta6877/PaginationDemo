package com.example.paginationdemo.network

import com.example.paginationdemo.model.ModelServerResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    fun getAllProducts(): Observable<ModelServerResponse>
}