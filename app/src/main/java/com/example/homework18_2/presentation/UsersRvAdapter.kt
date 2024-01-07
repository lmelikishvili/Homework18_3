package com.example.homework18_2.presentation

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework18_2.databinding.UserItemBinding
import com.example.homework18_2.domain.UserItem

class UsersRvAdapter : ListAdapter<UserItem, UsersRvAdapter.UserVH>(UsetDiffUtil()) {


    class UsetDiffUtil : DiffUtil.ItemCallback<UserItem>() {
        override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH = UserVH(
        UserItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: UserVH, position: Int) {
        holder.bind()
    }


    inner class UserVH(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var user: UserItem
        fun bind() {
            user = currentList[adapterPosition]
            d("currentListBody", "${user.first_name}")
            with(binding) {
                tvID.text = "ID ${user.id}"
                tvName.text = "Name: ${user.first_name} ${user.last_name}"
                tvEmail.text = "Email: ${user.email}"
            }
            Glide.with(binding.root)
                .load(user.avatar)
                .into(binding.userImage)
        }
    }
}