package ru.krirll.testtask.data.network.models

import com.google.gson.annotations.SerializedName

data class ImageItemDto(

    @SerializedName("image")
    val imageUrl: String
)