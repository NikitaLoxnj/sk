package com.example.smarthouse

import kotlinx.serialization.Serializable

@Serializable
data class Rooms(
    val id: Int,
    val home_id: Int,
    val name : String,
    val types : Int
)
