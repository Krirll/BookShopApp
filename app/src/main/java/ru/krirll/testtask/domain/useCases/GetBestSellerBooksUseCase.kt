package ru.krirll.testtask.domain.useCases

import ru.krirll.testtask.domain.repository.Repository

class GetBestSellerBooksUseCase (
    private val repository: Repository
) {

    suspend operator fun invoke() = repository.getBestSellerBooks()
}