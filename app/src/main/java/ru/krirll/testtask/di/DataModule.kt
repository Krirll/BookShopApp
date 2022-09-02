package ru.krirll.testtask.di

import org.koin.dsl.module
import ru.krirll.data.mappers.BookMapper
import ru.krirll.data.mappers.ImageMapper
import ru.krirll.data.network.ApiService
import ru.krirll.data.repository.RepositoryImpl
import ru.krirll.domain.repository.Repository

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