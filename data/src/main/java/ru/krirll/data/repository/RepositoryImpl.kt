package ru.krirll.data.repository

import ru.krirll.data.mappers.BookMapper
import ru.krirll.data.mappers.ImageMapper
import ru.krirll.data.network.ApiService
import ru.krirll.domain.entities.BookItem
import ru.krirll.domain.entities.ImageItem
import ru.krirll.domain.repository.Repository

class RepositoryImpl (
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