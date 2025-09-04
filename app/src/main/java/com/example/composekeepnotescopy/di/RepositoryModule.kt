package com.example.composekeepnotescopy.di

import com.example.composekeepnotescopy.data.repository.ToDoRepositoryImpl
import com.example.composekeepnotescopy.domain.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideToDoRepository(impl: ToDoRepositoryImpl): ToDoRepository = impl
}