package ru.krirll.testtask.di

import org.koin.dsl.module
import ru.krirll.domain.useCases.GetBestSellerBooksUseCase
import ru.krirll.domain.useCases.GetCarouselImagesUseCase
import ru.krirll.domain.useCases.GetSimilarBooksUseCase

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