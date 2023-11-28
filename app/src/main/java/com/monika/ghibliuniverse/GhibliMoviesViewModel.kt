package com.monika.ghibliuniverse

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.monika.ghibliuniverse.model.GhibliMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GhibliMoviesViewModel(private val repository: GhibliMovieRepository) : ViewModel() {
    private val _ghibliMovies = MutableStateFlow<List<GhibliMovies>>(
        repository.getGhibliMovies()
            .sortedBy { it.title }
    )
    val ghibliMovies: StateFlow<List<GhibliMovies>> get() = _ghibliMovies

    // fitur search
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun search(newQuery: String) {
        _query.value = newQuery
        _ghibliMovies.value = repository.searchMovie(_query.value)
            .sortedBy { it.title }
    }
}

class ViewModelFactory(private val repository: GhibliMovieRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GhibliMoviesViewModel::class.java)) {
            return GhibliMoviesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}