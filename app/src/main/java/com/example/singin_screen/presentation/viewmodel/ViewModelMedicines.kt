package com.example.singin_screen.presentation.viewmodel

import android.content.Context
import com.example.singin_screen.R
import com.example.singin_screen.data.model.database.DatabaseProvider
import com.example.singin_screen.domain.network.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import java.io.IOException

class ViewModelMedicines(
        private val context: Context,
        private val coroutineScope: CoroutineScope

    ){
    private val productsDao = DatabaseProvider.provideDatabse(context).productsDao()
        private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
        val screenState: StateFlow<ScreenState> = _screenState

        private var job: Job? = null

    @ExperimentalSerializationApi
    fun loadData(){
        job?.cancel()
        job = coroutineScope.launch {
            try {
                _screenState.value = ScreenState.Loading
                val products = try {
                    NetworkService(context).loadMedicines().also{
                        productsDao.insertALL(it)
                    }
                } catch (ex: IOException){
                    productsDao.getAll()
                }

                _screenState.value = ScreenState.DataLoaded(products)
            } catch (ex: Throwable){
                _screenState.emit(ScreenState.Error(context.resources.getString(R.string.error)))
            }
        }
    }
}