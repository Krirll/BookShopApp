package ru.krirll.testtask.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.krirll.testtask.presentation.viewModels.BookDetailsViewModel
import ru.krirll.testtask.presentation.viewModels.CarouselBestSellerViewModel

val appModule = module {

    viewModel {
        CarouselBestSellerViewModel(
            getBestSellerBooksUseCase = get(),
            getCarouselImagesUseCase = get()
        )
    }

    viewModel {
        BookDetailsViewModel(
            getSimilarBooksUseCase = get()
        )
    }

}