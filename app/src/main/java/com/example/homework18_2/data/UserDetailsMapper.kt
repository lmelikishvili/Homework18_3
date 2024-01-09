package com.example.homework18_2.data

import com.example.homework18_2.domain.UserDetails

fun UserDetailsDto.toDomain(): UserDetails {
    return UserDetails(
        data, support
    )
}