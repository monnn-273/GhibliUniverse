package com.monika.ghibliuniverse.data

import com.monika.ghibliuniverse.model.GhibliMovies
import com.monika.ghibliuniverse.model.GhibliMoviesData
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getGhibliMovies(): List<GhibliMovies> {
        return GhibliMoviesData.ghibliMovies
    }

    fun searchMovie(query: String): Flow<List<GhibliMovies>>

    fun getMovieById(id: Int): Flow<GhibliMovies>
}