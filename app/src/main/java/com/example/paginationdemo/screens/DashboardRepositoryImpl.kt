package com.example.paginationdemo.screens

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.paginationdemo.UserDatabase
import com.example.paginationdemo.model.ModelServerResponse
import com.example.paginationdemo.model.ModelUser
import com.example.paginationdemo.network.ApiBuilder
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DashboardRepositoryImpl(private val context: Context) : DashboardContracts.Repository {

    private var db: UserDatabase? = null

    init {
        db = UserDatabase.getDatabase(context)
    }

    override fun getDataFromAPI(apiResponseListener: GetApiResponseListener) {
        ApiBuilder.create().getAllProducts().subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<ModelServerResponse> {

                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(response: ModelServerResponse) {

                    response.userList?.forEach {
                        db?.userDao()?.insert(it)
                    }

                    apiResponseListener.onSuccess()
                }

                override fun onError(e: Throwable) {
                    e.message?.let {
                        apiResponseListener.onFail(it)
                    }
                }
            })
    }

    override fun fetchUserList():List<ModelUser>? {
        return db?.userDao()?.fetchAllUsers()
    }

    override fun clearAllTableData() {

    }

    interface GetApiResponseListener {

        fun onSuccess()

        fun onFail(message: String)
    }

}