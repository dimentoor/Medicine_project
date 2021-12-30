package com.example.singin_screen.data.model

import kotlinx.serialization.Serializable


@Serializable
data class Products(
        val name : String,
        val description : String,
        val iconUrl : String
    )
