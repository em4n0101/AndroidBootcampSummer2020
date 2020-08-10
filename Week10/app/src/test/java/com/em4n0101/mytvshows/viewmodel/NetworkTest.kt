package com.em4n0101.mytvshows.viewmodel

import com.em4n0101.mytvshows.app.modelModule
import com.em4n0101.mytvshows.app.networkModule
import com.em4n0101.mytvshows.networking.RemoteApi
import junit.framework.Assert.assertNotNull
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import retrofit2.Retrofit

class NetworkTest: KoinTest {
    private val remoteApi: RemoteApi by inject()
    private val retrofit: Retrofit by inject()
    private val okHttpClient: OkHttpClient by inject()
    private val baseUrl: String by lazy { get(named("BASE_URL")) as String }

    @Before
    fun setup() {
        startKoin {
            modules(listOf(networkModule, modelModule))
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `Test Retrofit Created`() {
        assertNotNull(retrofit)
        assert(baseUrl == "https://api.tvmaze.com")
    }

    @Test
    fun `Test API Created`() {
        assertNotNull(remoteApi)
    }

    @Test
    fun `Test OKHttp`() {
        assertNotNull(okHttpClient)
    }
}