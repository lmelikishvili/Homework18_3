package com.example.homework18_2.di

import com.example.homework18_2.data.UserRepositoryImpl
import com.example.homework18_2.data.UserService
import com.example.homework18_2.domain.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(userService: UserService): UserRepository{
        return UserRepositoryImpl(userService = userService)
    }
}