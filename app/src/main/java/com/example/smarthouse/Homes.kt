package com.example.smarthouse

import kotlinx.serialization.Serializable

@Serializable
data class Homes(
    val id : Int,
    val address :String
)
