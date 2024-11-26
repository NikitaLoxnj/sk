package com.example.smarthouse
import kotlinx.serialization.Serializable

@Serializable
data class RoomType(
    val id: Int =0,
    val typename: String ="",
    val icon :String=""
)
