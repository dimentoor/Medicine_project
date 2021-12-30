package com.example.singin_screen.presentation.viewmodel

import android.content.Context
import com.example.singin_screen.R
import com.example.singin_screen.domain.network.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class ViewModelDisinfectants (
    private val context: Context,
    private val coroutineScope: CoroutineScope

){
    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val screenState: StateFlow<ScreenState> = _screenState

    private var job: Job? = null

    @ExperimentalSerializationApi
    fun loadData(){
        job?.cancel()
        job = coroutineScope.launch {
            try {
                _screenState.emit(ScreenState.Loading)
                val disinfectants = NetworkService.loadDisinfectants()
                _screenState.emit(ScreenState.DataLoaded(disinfectants))
            } catch (ex: Throwable){
                _screenState.emit(ScreenState.Error(context.resources.getString(R.string.error)))
            }
        }
    }
}