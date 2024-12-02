package com.example.myapplication.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class JokeResponse(
    @SerializedName("jokes")
    val jokes : List<JokeItem>
)

@Serializable
data class JokeItem(
    @SerializedName("category")
    val category: String,
    @SerializedName("setup")
    val setup : String,
    @SerializedName("delivery")
    val delivery : String
)
