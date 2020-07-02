package com.example.paginationdemo.screens

import androidx.lifecycle.LiveData
import com.example.paginationdemo.model.ModelUser

interface DashboardContracts {
    interface View {
        fun showProgressDialog(isShown: Boolean)

        fun showUserList(userList: MutableList<ModelUser>)

        fun setUpInitialUi()

        fun showNativeAlert(message: String)
    }

    interface Presenter {

        fun onLoad(isOnline: Boolean)

        fun setUserList()

        fun setUpInitialUi()
    }

    interface Repository {

        fun getDataFromAPI(apiResponseListener: DashboardRepositoryImpl.GetApiResponseListener)

        fun fetchUserList():List<ModelUser>?

        fun clearAllTableData()
    }
}