package ru.krirll.testtask.di.module

import dagger.Binds
import dagger.Module
import ru.krirll.testtask.data.repository.RepositoryImpl
import ru.krirll.testtask.domain.repository.Repository

@Module
interface DomainModule {

    @Binds
    fun bindRepository(impl: RepositoryImpl): Repository
}