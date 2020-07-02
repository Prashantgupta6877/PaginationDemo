package com.example.paginationdemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.paginationdemo.dao.UserDao
import com.example.paginationdemo.model.ModelUser

@Database(entities = [ModelUser::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase? {
            if (INSTANCE == null) {
                synchronized(UserDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java, "userInfo.db"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}
