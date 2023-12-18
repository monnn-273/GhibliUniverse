package com.monika.ghibliuniverse.di

import com.monika.ghibliuniverse.data.GhibliMovieRepository
import com.monika.ghibliuniverse.data.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Module Dagger yang memberikan dependency injection untuk repository.
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

   // Mengikat implementasi GhibliMovieRepository ke interface Repository sehingga memungkinkan Dagger untuk menyediakan instance GhibliMovieRepository sebagai implementasi Repository di seluruh aplikasi.
   @Binds
   @Singleton
   abstract fun provideGhibliMovieRepository(repository: GhibliMovieRepository): Repository
}