package ru.krirll.testtask.di.module

import dagger.Binds
import dagger.Module
import ru.krirll.testtask.data.repository.RepositoryImpl
import ru.krirll.testtask.domain.repository.Repository

@Module
interface DomainModule {

    //TODO разбить на два разных Datasource (local & remote)

    @Binds
    fun bindRepository(impl: RepositoryImpl): Repository
}