package com.example.singin_screen.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.serialization.Serializable


@Serializable
@Entity(primaryKeys = ["name", "description", "iconUrl"])
data class Products(
    @ColumnInfo val name : String,
    @ColumnInfo val description : String,
    @ColumnInfo val iconUrl : String
    )
