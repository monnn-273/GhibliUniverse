package com.monika.ghibliuniverse.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monika.ghibliuniverse.data.Repository
import com.monika.ghibliuniverse.model.GhibliMovies
import com.monika.ghibliuniverse.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<GhibliMovies>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<GhibliMovies>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() =_query
    fun searchMovie(newQuery: String) = viewModelScope.launch {
        _query.value = newQuery
        repository.searchMovie(_query.value)
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect{
                _uiState.value = UiState.Success(it)
            }
    }
}
