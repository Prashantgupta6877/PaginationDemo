package com.example.paginationdemo.screens

class DashboardPresenterImpl(
    private val view: DashboardContracts.View,
    private val repository: DashboardContracts.Repository
) : DashboardContracts.Presenter,
    DashboardRepositoryImpl.GetApiResponseListener {


    override fun setUpInitialUi() {
        view.setUpInitialUi()
    }

    override fun onLoad(isOnline: Boolean) {

        if (isOnline) {
            view.showProgressDialog(true)
            repository.getDataFromAPI(this)
        } else {
            setUserList()
        }
    }

    override fun onSuccess() {
        view.showProgressDialog(false)
        setUserList()
    }

    override fun onFail(message: String) {
        view.showProgressDialog(false)
        view.showNativeAlert(message)
    }

    override fun setUserList() {
        repository.fetchUserList()?.let {
            view.showUserList(it.toMutableList())
        }
    }
}