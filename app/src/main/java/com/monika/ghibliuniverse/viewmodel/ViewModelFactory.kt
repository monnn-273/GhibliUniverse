package com.monika.ghibliuniverse.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.monika.ghibliuniverse.data.GhibliMovieRepository

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