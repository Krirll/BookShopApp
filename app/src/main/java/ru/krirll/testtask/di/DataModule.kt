package ru.krirll.testtask.di

import org.koin.dsl.module
import ru.krirll.testtask.data.mappers.BookMapper
import ru.krirll.testtask.data.mappers.ImageMapper
import ru.krirll.testtask.data.network.ApiService
import ru.krirll.testtask.data.repository.RepositoryImpl
import ru.krirll.testtask.domain.repository.Repository

val dataModule = module {

    factory {
        BookMapper()
    }

    factory {
        ImageMapper()
    }

    single {
        ApiService.getApiServiceInstance()
    }

    single<Repository> {
        RepositoryImpl(
            bookMapper = get(),
            imageMapper = get(),
            service = get()
        )
    }

}