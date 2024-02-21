package com.example.alne.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class food (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
)