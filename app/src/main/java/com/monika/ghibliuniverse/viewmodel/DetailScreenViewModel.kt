package com.monika.ghibliuniverse.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monika.ghibliuniverse.data.Repository
import com.monika.ghibliuniverse.model.GhibliMovies
import com.monika.ghibliuniverse.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.catch

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val repository: Repository
): ViewModel(){
    private val _uiState: MutableStateFlow<UiState<GhibliMovies>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<GhibliMovies>>
        get() = _uiState

    fun getMovieById(MovieId: Int){
        viewModelScope.launch{
            repository.getMovieById(MovieId).catch{
                _uiState.value = UiState.Error(it.message.toString())}
                .collect {
                    _uiState.value = UiState.Success(it)}
        }

    }
}