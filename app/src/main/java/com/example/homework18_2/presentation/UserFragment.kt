package com.example.homework18_2.presentation

import android.util.Log.d
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework18_2.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate) {
    private val viewModel: UserViewModel by viewModels()
    private val adapter: UsersRvAdapter by lazy {
        UsersRvAdapter()
    }
    override fun setup() {
        viewModel.getUsers()
        viewModel.getUserDetails()
        with(binding) {
            usersRecyclerView.adapter = adapter
            usersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun setupListeners() {

    }

    override fun bindData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userFlow.collect {
                    adapter.submitList(it.data)
                    d("itBody", "${it.data}")
                }
            }
        }
    }
}