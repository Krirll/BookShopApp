package ru.krirll.testtask.data.mappers

import ru.krirll.testtask.data.network.models.ImageItemDto
import ru.krirll.testtask.domain.entities.ImageItem
import javax.inject.Inject

class ImageMapper @Inject constructor() {

    fun mapFromDtoToEntity(dto: ImageItemDto, isCarousel: Boolean) = ImageItem(
        imageUrl = dto.imageUrl,
        isCarousel = isCarousel
    )
}