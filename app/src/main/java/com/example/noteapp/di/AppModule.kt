package com.example.noteapp.di

import android.app.Application
import com.example.movieapp.Constants
import com.example.movieapp.api.ApiService
import com.example.noteapp.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = NoteDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideNoteDao(database: NoteDatabase) = database.getNoteDao()

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL: String): ApiService =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

}