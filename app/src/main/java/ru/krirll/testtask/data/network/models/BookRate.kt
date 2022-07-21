package ru.krirll.testtask.data.network.models

import com.google.gson.annotations.SerializedName

data class BookRate(

    @SerializedName("score")
    val score: Float,

    @SerializedName("amount")
    val amount: Int
)