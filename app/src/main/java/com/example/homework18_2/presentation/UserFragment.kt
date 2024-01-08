package com.example.homework18_2.presentation

import android.util.Log.d
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework18_2.databinding.FragmentUserBinding
import com.example.homework18_2.domain.UserItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate) {
    private val viewModel: UserViewModel by viewModels()
    private val adapter: UsersRvAdapter by lazy {
        UsersRvAdapter()
    }
    private var userList = mutableListOf<UserItem>()
    override fun setup() {
        viewModel.getUsers()
        viewModel.getUserDetails()
        with(binding) {
            usersRecyclerView.adapter = adapter
            usersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }


    override fun setupListeners() {
        binding.btnDelete.setOnClickListener(){
            //var newList =  listOf(userList.removeAt(2))
            userList.removeAt(2)
            //adapter.submitList(userList)
            adapter.notifyDataSetChanged()
        }
    }

    override fun bindData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userFlow.collect {
                    userList = it.data!!.toMutableList()
                    adapter.submitList(userList)
                    d("itBody", "$userList")
                }
            }
        }
    }
}