package com.monika.ghibliuniverse.di

import com.monika.ghibliuniverse.data.GhibliMovieRepository
import com.monika.ghibliuniverse.data.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

   @Binds
   @Singleton
   abstract fun provideGhibliMovieRepository(repository: GhibliMovieRepository): Repository
}