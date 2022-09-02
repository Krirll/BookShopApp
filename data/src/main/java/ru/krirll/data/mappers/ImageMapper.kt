package ru.krirll.data.mappers

import ru.krirll.data.network.models.ImageItemDto
import ru.krirll.domain.entities.ImageItem

class ImageMapper {

    fun mapFromDtoToEntity(dto: ImageItemDto, isCarousel: Boolean) = ImageItem(
        imageUrl = dto.imageUrl,
        isCarousel = isCarousel
    )
}