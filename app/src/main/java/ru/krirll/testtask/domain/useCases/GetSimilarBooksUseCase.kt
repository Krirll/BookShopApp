package ru.krirll.testtask.domain.useCases

import ru.krirll.testtask.domain.repository.Repository
import javax.inject.Inject

class GetSimilarBooksUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke() = repository.getSimilarImages()
}