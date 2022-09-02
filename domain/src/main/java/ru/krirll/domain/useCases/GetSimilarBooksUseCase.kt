package ru.krirll.domain.useCases

import ru.krirll.domain.repository.Repository

class GetSimilarBooksUseCase (
    private val repository: Repository
) {

    suspend operator fun invoke() = repository.getSimilarImages()
}