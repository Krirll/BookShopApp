package ru.krirll.testtask

import android.app.Application
import ru.krirll.testtask.di.DaggerApplicationComponent

class MainApplication: Application() {

    val component by lazy {
        DaggerApplicationComponent.create()
    }
}