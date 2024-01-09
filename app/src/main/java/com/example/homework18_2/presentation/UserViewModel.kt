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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private val _userFlow = MutableStateFlow<Resource<List<UserItem>>>(Resource.Success(data = emptyList()))
    val userFlow: SharedFlow<Resource<List<UserItem>>> = _userFlow.asStateFlow()

//    private val _deleteUserFlow = MutableStateFlow<List<UserItem>>(emptyList())
//    val deleteUserFlow: SharedFlow<List<UserItem>> = _deleteUserFlow

    private val _deleteUserFlow = MutableStateFlow<List<UserItem>>(emptyList())
    val deleteUserFlow: SharedFlow<List<UserItem>> = _deleteUserFlow


    fun getUsers(){
        viewModelScope.launch {
            userRepository.getUsers().collect{
                when(it){
                    //is Resource.Loading -> _userFlow.value = Resource.Loading(loading = it.loading)
                    is Resource.Success -> _userFlow.value = Resource.Success(data = it.data!!)
                    //is Resource.Error -> _userFlow.value = Resource.Error(errorMessage = it.errorMessage!!)
                    else -> {d("dataLog", "${it.data}")}
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

    fun deleteUser(){
        var activeUsers = mutableListOf<UserItem>()
        viewModelScope.launch {
            userRepository.deleteUser().collect{
                for (user in _userFlow.value.data!!){
                    println(user)
                    if (user.id != it.data.id){
                        activeUsers.add(user)
                        //_deleteUserFlow.emit(listOf(UserItem(user.avatar,user.email,user.first_name,user.id,user.last_name)))
                        println("User Must Delete")
                    }
                }
                d("deleteUserDetails", "${it.data}")
            }
            _deleteUserFlow.emit(activeUsers)
        }
    }

    fun deleteUser2(){
        _userFlow.map {
            for (i in it.data!!){
                if (i.id != 7){
                    _deleteUserFlow.emit(listOf(UserItem(i.avatar,i.email,i.first_name,i.id,i.last_name)))
                }else{
                    println(i)
                }
            }
        }
    }
}