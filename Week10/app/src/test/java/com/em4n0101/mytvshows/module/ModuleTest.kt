package com.em4n0101.mytvshows.module

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.em4n0101.mytvshows.app.modelModule
import com.em4n0101.mytvshows.app.networkModule
import com.em4n0101.mytvshows.app.viewModelsModule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class ModuleTest: KoinTest {
    private val appContext = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun `Test Koin Modules`() {
        startKoin {
            androidContext(appContext)
            modules(listOf(networkModule, modelModule, viewModelsModule))
        }.checkModules()
        stopKoin()
    }
}