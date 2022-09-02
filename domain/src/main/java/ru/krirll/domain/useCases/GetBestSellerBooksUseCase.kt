package ru.krirll.domain.useCases

import ru.krirll.domain.repository.Repository

class GetBestSellerBooksUseCase (
    private val repository: Repository
) {

    suspend operator fun invoke() = repository.getBestSellerBooks()
}