package com.example.paginationdemo.model

import com.google.gson.annotations.SerializedName

data class ModelServerResponse(
    @SerializedName("page")
    var page: Int,
    @SerializedName("per_page")
    var perPage: Int,
    @SerializedName("total")
    var total: Int,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("data")
    var userList: List<ModelUser>?
)