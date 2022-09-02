package ru.krirll.domain.repository

import ru.krirll.domain.entities.BookItem
import ru.krirll.domain.entities.ImageItem

interface Repository {

    suspend fun getBestSellerBooks(): List<BookItem>

    suspend fun getCarouselImages(): List<ImageItem>

    suspend fun getSimilarImages(): List<ImageItem>
}