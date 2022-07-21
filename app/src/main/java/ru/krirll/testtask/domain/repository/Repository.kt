package ru.krirll.testtask.domain.repository

import ru.krirll.testtask.domain.entities.BookItem
import ru.krirll.testtask.domain.entities.ImageItem

interface Repository {

    suspend fun getBestSellerBooks(): List<BookItem>

    suspend fun getCarouselImages(): List<ImageItem>

    suspend fun getSimilarImages(): List<ImageItem>
}