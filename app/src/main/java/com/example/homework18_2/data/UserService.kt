package com.example.homework18_2.data

import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("7ec14eae-06bf-4f6d-86d2-ac1b9df5fe3d")
    suspend fun getUsers(): Response<List<UserDto>>

    @GET("https://reqres.in/api/users/7")
    suspend fun getUserDetails(): Response<UserDetailsDto>

    @GET("https://reqres.in/api/users/7")
    suspend fun deleteUser(): Response<UserDetailsDto>
}