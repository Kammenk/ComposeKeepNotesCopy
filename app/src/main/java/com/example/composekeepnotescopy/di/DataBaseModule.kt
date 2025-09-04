package com.example.composekeepnotescopy.di

import android.content.Context
import androidx.room.Room
import com.example.composekeepnotescopy.data.db.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideToDoDatabase(@ApplicationContext context: Context): ToDoDatabase {
        return Room.databaseBuilder(
                context.applicationContext,
                ToDoDatabase::class.java,
                "todo_database"
            ).fallbackToDestructiveMigration(false)
            .build()
    }


    @Provides
    fun provideToDoDao(database: ToDoDatabase) = database.toDoDao()
}