package com.example.paginationdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationdemo.R
import com.example.paginationdemo.model.ModelUser
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.view.*

class UserAdapter(private val userList: List<ModelUser>) :
    PagedListAdapter<ModelUser, UserAdapter.ViewHolder>(diff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        )
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelUser: ModelUser? = userList[position]
        holder.setName(modelUser?.firstName ?: "", modelUser?.lastName ?: "")
        holder.setEmail(modelUser?.emailId ?: "")
        holder.setProfilePic(modelUser?.picUrl?:"")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setName(firstName: String, lastName: String) {
            itemView.txtName.text = firstName.plus(" ").plus(lastName)
        }

        fun setEmail(email: String) {
            itemView.txtEmail.text = email
        }

        fun setProfilePic(url: String) {
            Picasso.with(itemView.context).load(url).into(itemView.imgPic)
        }
    }
}

val diff = object : DiffUtil.ItemCallback<ModelUser>() {
    override fun areItemsTheSame(oldItem: ModelUser, newItem: ModelUser) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ModelUser, newItem: ModelUser) = oldItem == newItem
}