package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//data class Joke(
//    val id: String,
//    val category: String,
//    val question: String,
//    val answer: String
//)

@Serializable
@Entity (tableName = "jokes")
data class Joke(
    @SerialName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerialName("category")
    val category: String,
    @SerialName("setup")
    val setup: String,
    @SerialName("delivery")
    val delivery: String
)