package ru.krirll.testtask.domain.useCases

import ru.krirll.testtask.domain.repository.Repository
import javax.inject.Inject

class GetBestSellerBooksUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke() = repository.getBestSellerBooks()
}