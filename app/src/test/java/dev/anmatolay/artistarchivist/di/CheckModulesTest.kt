package dev.anmatolay.artistarchivist.di

import androidx.lifecycle.SavedStateHandle
import dev.anmatolay.artistarchivist.core.di.AppModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.ksp.generated.module
import org.koin.test.verify.verify

class CheckModulesTest {

    @OptIn(KoinExperimentalAPI::class, ExperimentalCoroutinesApi::class)
    @Test
    fun checkAllModules() {
        AppModule().module.apply {
            this.plus(module { single { StandardTestDispatcher() } })
        }.verify(
            extraTypes = listOf(
                SavedStateHandle::class,
                CoroutineDispatcher::class,
            ))
    }
}