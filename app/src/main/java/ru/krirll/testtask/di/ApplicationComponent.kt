package ru.krirll.testtask.di

import dagger.Component
import ru.krirll.testtask.di.module.DataModule
import ru.krirll.testtask.di.module.DomainModule
import ru.krirll.testtask.di.module.ViewModelModule
import ru.krirll.testtask.di.scope.ApplicationScope
import ru.krirll.testtask.presentation.fragments.BookDetailsFragment
import ru.krirll.testtask.presentation.fragments.CarouselBestSellersFragment

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(carouselBestSellersFragment: CarouselBestSellersFragment)

    fun inject(bookDetailsFragment: BookDetailsFragment)
}