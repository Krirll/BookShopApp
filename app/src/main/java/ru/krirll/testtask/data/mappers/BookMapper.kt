package ru.krirll.testtask.data.mappers

import ru.krirll.testtask.data.network.models.BookItemDto
import ru.krirll.testtask.domain.entities.BookItem
import javax.inject.Inject

class BookMapper @Inject constructor() {

    fun mapFromDtoToEntity(dto: BookItemDto) = BookItem(
        title = dto.title,
        author = dto.author,
        price = dto.price,
        imageUrl = dto.imageUrl,
        score = dto.rate.score.toString(),
        amount = dto.rate.amount
    )
}