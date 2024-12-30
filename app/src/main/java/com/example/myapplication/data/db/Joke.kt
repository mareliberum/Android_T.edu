package com.example.myapplication.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import java.io.Serializable


@kotlinx.serialization.Serializable
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
    val delivery: String,
    @SerialName("isFromNet")
    val isFromNet: Boolean,
    @SerialName("isFavourite")
    var isFavourite: Boolean,

    @SerialName("timeStamp")
    val timeStamp: Long = System.currentTimeMillis()
) : Serializable