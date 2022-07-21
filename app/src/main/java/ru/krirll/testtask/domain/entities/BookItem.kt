package ru.krirll.testtask.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookItem(
    val title: String,
    val author: String,
    val price: Float,
    val imageUrl: String,
    val score: String,
    val amount: Int
): Parcelable
