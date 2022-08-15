package ru.krirll.testtask.data.repository

import ru.krirll.testtask.data.mappers.BookMapper
import ru.krirll.testtask.data.mappers.ImageMapper
import ru.krirll.testtask.data.network.ApiService
import ru.krirll.testtask.domain.entities.BookItem
import ru.krirll.testtask.domain.entities.ImageItem
import ru.krirll.testtask.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val imageMapper: ImageMapper,
    private val bookMapper: BookMapper
): Repository {

    override suspend fun getBestSellerBooks(): List<BookItem> =
        service.getBestSellerBooks().map {
            bookMapper.mapFromDtoToEntity(it)
        }

    override suspend fun getCarouselImages(): List<ImageItem> =
        service.getCarouselImages().map {
            imageMapper.mapFromDtoToEntity(it, true)
        }

    override suspend fun getSimilarImages(): List<ImageItem> =
        service.getSimilarBooks().map {
            imageMapper.mapFromDtoToEntity(it, false)
        }
}