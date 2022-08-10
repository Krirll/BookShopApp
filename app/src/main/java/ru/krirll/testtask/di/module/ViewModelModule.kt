package ru.krirll.testtask.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.krirll.testtask.presentation.viewModels.BookDetailsViewModel
import ru.krirll.testtask.presentation.viewModels.CarouselBestSellerViewModel
import ru.krirll.testtask.presentation.viewModels.ViewModelKey

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CarouselBestSellerViewModel::class)
    @Binds
    fun bindCarouselBestSellerViewModel(impl: CarouselBestSellerViewModel): ViewModel

    @IntoMap
    @ViewModelKey(BookDetailsViewModel::class)
    @Binds
    fun bindBookDetailsViewModel(impl: BookDetailsViewModel): ViewModel
}