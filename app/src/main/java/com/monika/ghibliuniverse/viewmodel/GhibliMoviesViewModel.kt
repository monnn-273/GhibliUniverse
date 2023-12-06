package com.monika.ghibliuniverse.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.monika.ghibliuniverse.data.GhibliMovieRepository
import com.monika.ghibliuniverse.model.GhibliMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GhibliMoviesViewModel(private val repository: GhibliMovieRepository) : ViewModel() {
    private val _groupedMovies = MutableStateFlow(
        repository.getGhibliMovies()
            .sortedBy { it.title }
            .groupBy{it.title[0]}
    )

    val groupedMovies: StateFlow<Map<Char, List<GhibliMovies>>> get() = _groupedMovies

    // fitur search
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun search(newQuery: String) {
        _query.value = newQuery
        _ghibliMovies.value = repository.searchMovie(_query.value)
            .sortedBy { it.title }
    }
}