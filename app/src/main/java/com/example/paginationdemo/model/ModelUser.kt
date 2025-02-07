package com.example.paginationdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "User")
data class ModelUser(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "email")
    @SerializedName("email")
    var emailId: String = "",

    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    var firstName: String = "",

    @ColumnInfo(name = "last_name")
    @SerializedName("last_name")
    var lastName: String = "",

    @ColumnInfo(name = "avatar")
    @SerializedName("avatar")
    var picUrl: String = ""
)