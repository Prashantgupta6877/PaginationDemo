package com.example.paginationdemo.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paginationdemo.model.ModelUser

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(chapter: ModelUser)

    @Query("SELECT * FROM User")
    fun fetchAllUsers(): List<ModelUser>
}