package com.example.homework18_2.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework18_2.databinding.UserItemBinding
import com.example.homework18_2.domain.UserDetails

class DeleteUserAdapter  : ListAdapter<UserDetails, DeleteUserAdapter.UserVH>(UsetDiffUtil()) {


    class UsetDiffUtil : DiffUtil.ItemCallback<UserDetails>() {
        override fun areItemsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean {
            return oldItem.data.id == newItem.data.id
        }

        override fun areContentsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH = UserVH(
        UserItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: DeleteUserAdapter.UserVH, position: Int) {
        holder.bind()
    }



    inner class UserVH(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var user: UserDetails
        fun bind() {
            user = currentList[adapterPosition]
            Log.d("currentListBody", "${user.data.first_name}")
            with(binding) {
                tvID.text = "ID ${user.data.id}"
                tvName.text = "Name: ${user.data.first_name} ${user.data.last_name}"
                tvEmail.text = "Email: ${user.data.email}"
            }
            Glide.with(binding.root)
                .load(user.data.avatar)
                .into(binding.userImage)
        }
    }
}