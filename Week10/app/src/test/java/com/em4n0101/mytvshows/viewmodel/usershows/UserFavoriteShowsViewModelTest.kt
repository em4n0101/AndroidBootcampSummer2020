package com.em4n0101.mytvshows.viewmodel.usershows

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.em4n0101.mytvshows.app.modelModule
import com.em4n0101.mytvshows.app.networkModule

import com.em4n0101.mytvshows.model.Show
import com.em4n0101.mytvshows.model.repositories.ShowsRepository
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE)
class UserFavoriteShowsViewModelTest: KoinTest {

    private val appContext = ApplicationProvider.getApplicationContext<Context>()
    private lateinit var viewModel: UserFavoriteShowsViewModel

    private val repository: ShowsRepository by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockProvider = MockProviderRule.create {
        Mockito.mock(it.java)
    }

    private val showList = listOf(
        Show(1, "Game of thrones", arrayOf("drama"), "Summary demo","10-10-10", null, null, null),
        Show(2, "Fleabag", arrayOf("comedy"), "Summary demo","10-10-10", null, null, null)
    )

    @Before
    fun setup() {
        startKoin {
            androidContext(appContext)
            modules(listOf(modelModule, networkModule))
        }
        declareMock<ShowsRepository>()
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(repository.getShows()).thenReturn(flowOf(showList))
        viewModel = UserFavoriteShowsViewModel(repository)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun getPersons() {
        viewModel.getShows().observeForever {
            it.forEach(::println)
            assertEquals(it.size, 2)
        }
    }
}