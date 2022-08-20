package ru.krirll.testtask.presentation.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.krirll.testtask.di.appModule
import ru.krirll.testtask.di.dataModule
import ru.krirll.testtask.di.domainModule

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule, dataModule, domainModule)
        }
    }
}