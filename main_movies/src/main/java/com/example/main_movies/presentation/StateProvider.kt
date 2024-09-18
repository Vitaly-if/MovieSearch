package com.example.main_movies.presentation

import kotlinx.coroutines.flow.StateFlow

interface StateProvider<T> {
    val state: StateFlow<T>
}