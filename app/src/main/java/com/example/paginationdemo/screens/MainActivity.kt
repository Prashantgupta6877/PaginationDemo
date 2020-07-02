package com.example.paginationdemo.screens

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.paginationdemo.R
import com.example.paginationdemo.adapter.UserAdapter
import com.example.paginationdemo.model.ModelUser
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager as LinearLayoutManager1

class MainActivity : AppCompatActivity(), DashboardContracts.View {

    private lateinit var presenter: DashboardContracts.Presenter
    private lateinit var userAdapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = DashboardPresenterImpl(this, DashboardRepositoryImpl(this))
        presenter.setUpInitialUi()
        presenter.onLoad(isNetworkAvailable())
    }

    override fun setUpInitialUi() {
        rcyData.layoutManager = LinearLayoutManager1(this)
        rcyData.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    override fun showProgressDialog(isShown: Boolean) {
        progressBar.visibility = if (isShown) VISIBLE else GONE
    }

    override fun showUserList(userList: MutableList<ModelUser>) {
        userAdapter = UserAdapter(userList)
        rcyData.adapter = userAdapter
    }

    override fun showNativeAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setNeutralButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
