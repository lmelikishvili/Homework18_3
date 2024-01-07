package com.example.homework18_2.data

import com.example.homework18_2.domain.UserItem

fun UserDto.toDomain(): UserItem{
    return UserItem(
        avatar, email, first_name, id, last_name
    )
}