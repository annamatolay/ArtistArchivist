package dev.anmatolay.artistarchivist

import android.app.Application
import dev.anmatolay.artistarchivist.core.di.KoinInitializer
import dev.anmatolay.artistarchivist.core.logging.DiamondDebugTree
import dev.anmatolay.artistarchivist.core.logging.FakeCrashReportTree
import timber.log.Timber

class ArtistArchivistApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KoinInitializer.init(this)

        // I would setup analytics and logging here (if it would be a real app)
        // Now only setup logging for debug, otherwise log nothing
        // If you would like to see how I would do it you can check here:
        // Github - annamatolay/template-android-xml - domain/usecase/MonitoringUseCase.kt
        // https://urlr.me/79kjB
        Timber.plant(if (BuildConfig.DEBUG) DiamondDebugTree() else FakeCrashReportTree())
    }
}
