package com.example.homework18_2.presentation

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework18_2.data.common.Resource
import com.example.homework18_2.domain.UserItem
import com.example.homework18_2.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private val _userFlow = MutableStateFlow<Resource<List<UserItem>>>(Resource.Success(data = emptyList()))
    val userFlow: SharedFlow<Resource<List<UserItem>>> = _userFlow.asStateFlow()

    fun getUsers(){
        viewModelScope.launch {
            userRepository.getUsers().collect{
                when(it){
                    is Resource.Loading -> _userFlow.value = Resource.Loading(loading = it.loading)
                    is Resource.Success -> _userFlow.value = Resource.Success(data = it.data!!)
                    is Resource.Error -> _userFlow.value = Resource.Error(errorMessage = it.errorMessage!!)
                }
            }
        }
    }

    fun getUserDetails(){
        viewModelScope.launch {
            userRepository.getUserDetails().collect{
                d("collectedUserDetails", "${it.data}")
            }
        }
    }
}