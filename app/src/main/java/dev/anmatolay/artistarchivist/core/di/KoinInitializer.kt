package dev.anmatolay.artistarchivist.core.di

import android.app.Application
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.ksp.generated.module

object KoinInitializer {

    fun init(application: Application) {
        startKoin {
            androidLogger()
            androidContext(application)
            modules(AppModule().module.plus(module { single { Dispatchers.IO } }))
        }
    }
}
