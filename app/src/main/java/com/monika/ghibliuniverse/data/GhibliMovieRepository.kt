package com.monika.ghibliuniverse.data

import com.monika.ghibliuniverse.model.GhibliMovies
import com.monika.ghibliuniverse.model.GhibliMoviesData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

//  berfungsi sebagai repository untuk data movie dan mengimplementasikan interface Repository
@Singleton
class GhibliMovieRepository @Inject constructor(): Repository {

    private val movies = mutableListOf<GhibliMovies>()

    init {
        if (movies.isEmpty()) {
            movies.addAll(GhibliMoviesData.ghibliMovies)
        }
    }

    override fun searchMovie(query: String) = flow{
           val GhibliMoviesData = movies.filter{
               it.title.contains(query, ignoreCase = true)
            }
            emit(GhibliMoviesData)
    }

    override fun getMovieById(id: Int): Flow<GhibliMovies> {
        return flowOf(movies.first{
            it.id == id
        })
    }
}