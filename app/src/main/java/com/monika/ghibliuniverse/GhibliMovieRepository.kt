package com.monika.ghibliuniverse

import com.monika.ghibliuniverse.model.GhibliMovies
import com.monika.ghibliuniverse.model.GhibliMoviesData

class GhibliMovieRepository {
    fun getGhibliMovies(): List<GhibliMovies> {
        return GhibliMoviesData.ghibliMovies
    }

    fun searchMovie(query: String): List<GhibliMovies>{
        return GhibliMoviesData.ghibliMovies.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }
}