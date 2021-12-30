package com.example.singin_screen.presentation.viewmodel

import com.example.singin_screen.data.model.Products

sealed class ScreenState {
    data class DataLoaded(val products: List<Products>) : ScreenState()
    object Loading : ScreenState()
    data class Error(val error: String) : ScreenState()
}