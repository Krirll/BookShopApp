package ru.krirll.testtask.data.network.models

import com.google.gson.annotations.SerializedName

data class BookItemDto(

    @SerializedName("title")
    val title: String,

    @SerializedName("author")
    val author: String,

    @SerializedName("price")
    val price: Float,

    @SerializedName("image")
    val imageUrl: String,

    @SerializedName("rate")
    val rate: BookRate
)