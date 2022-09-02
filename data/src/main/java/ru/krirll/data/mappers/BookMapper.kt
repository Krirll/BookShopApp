package ru.krirll.data.mappers

import ru.krirll.data.network.models.BookItemDto
import ru.krirll.domain.entities.BookItem

class BookMapper {

    fun mapFromDtoToEntity(dto: BookItemDto) = BookItem(
        title = dto.title,
        author = dto.author,
        price = dto.price,
        imageUrl = dto.imageUrl,
        score = dto.rate.score.toString(),
        amount = dto.rate.amount
    )
}