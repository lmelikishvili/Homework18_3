package com.example.homework18_2.data

import com.example.homework18_2.presentation.UserDetails

fun UserDetailsDto.toDomain(): UserDetails{
    return UserDetails(
        data, support
    )
}