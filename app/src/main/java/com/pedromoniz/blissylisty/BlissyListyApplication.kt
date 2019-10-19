package com.pedromoniz.blissylisty

import android.app.Application
import com.pedromoniz.blissylisty.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class BlissyListyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BlissyListyApplication)
            modules(listOf(apiModule, databaseModule, repositoriesModule, interactorModule, viewModelsModule))
        }

    }
}