package com.example.singin_screen.network

import com.example.singin_screen.model.Products
import retrofit2.http.GET

interface RestApi {
    @GET("medicines")
    suspend fun loadMedicines(): List<Products>
    @GET("biologicalactadd")
    suspend fun loadBiologicalactadd(): List<Products>
    @GET("disinfectants")
    suspend fun loadDisinfectants(): List<Products>

}