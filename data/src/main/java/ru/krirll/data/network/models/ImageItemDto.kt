package ru.krirll.data.network.models

import com.google.gson.annotations.SerializedName

data class ImageItemDto(

    @SerializedName("image")
    val imageUrl: String
)