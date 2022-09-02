package ru.krirll.domain.entities

import java.io.Serializable

data class BookItem(
    val title: String,
    val author: String,
    val price: Float,
    val imageUrl: String,
    val score: String,
    val amount: Int
): Serializable
