package com.example.homework18_2.domain

import com.example.homework18_2.data.common.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<Resource<List<UserItem>>>

    fun getUserDetails(): Flow<UserDetails>

    fun deleteUser(): Flow<UserDetails>
}