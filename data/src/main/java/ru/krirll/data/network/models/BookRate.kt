package ru.krirll.data.network.models

import com.google.gson.annotations.SerializedName

data class BookRate(

    @SerializedName("score")
    val score: Float,

    @SerializedName("amount")
    val amount: Int
)