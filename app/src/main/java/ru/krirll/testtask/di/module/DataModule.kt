package ru.krirll.testtask.di.module

import dagger.Module
import dagger.Provides
import ru.krirll.testtask.data.network.ApiService
import ru.krirll.testtask.di.scope.ApplicationScope

@Module
interface DataModule {

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService = ApiService.getApiServiceInstance()
    }
}