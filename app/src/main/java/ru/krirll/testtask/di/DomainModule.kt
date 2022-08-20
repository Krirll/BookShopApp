package ru.krirll.testtask.di

import org.koin.dsl.module
import ru.krirll.testtask.domain.useCases.GetBestSellerBooksUseCase
import ru.krirll.testtask.domain.useCases.GetCarouselImagesUseCase
import ru.krirll.testtask.domain.useCases.GetSimilarBooksUseCase

val domainModule = module {

    factory {
        GetBestSellerBooksUseCase(get())
    }

    factory {
        GetCarouselImagesUseCase(get())
    }

    factory {
        GetSimilarBooksUseCase(get())
    }

}