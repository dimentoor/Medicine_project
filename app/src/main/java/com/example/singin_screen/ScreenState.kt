package com.example.singin_screen

import com.example.singin_screen.model.Products

sealed class ScreenState {
    data class DataLoaded(val products: List<Products>) : ScreenState()
    object Loading : ScreenState()
    data class Error(val error: String) : ScreenState()
}