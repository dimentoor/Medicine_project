package com.example.singin_screen.model

import androidx.annotation.DrawableRes

data class Products(
        val name : String,
        val description : String,
        @DrawableRes
        val picture_medicines : Int
    )
