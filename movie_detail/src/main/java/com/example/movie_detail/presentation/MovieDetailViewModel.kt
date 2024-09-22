package com.example.movie_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_detail.domain.LoadMovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class MovieDetailViewModel(private val loadMovieUseCase: LoadMovieUseCase) : ViewModel(),
    KoinComponent {

    private val _state = MutableStateFlow<DetailMovieUiState>(DetailMovieUiState.Empty)
    val state: StateFlow<DetailMovieUiState>
        get() = _state

    fun initState() {
        _state.update { DetailMovieUiState.Empty }
    }

    fun loadMovie() {
        viewModelScope.launch {
            loadMovieUseCase.invoke().collect { detailEntity ->
                _state.update {
                    DetailMovieUiState.Success(detailEntity)
                }
            }
        }
    }
}