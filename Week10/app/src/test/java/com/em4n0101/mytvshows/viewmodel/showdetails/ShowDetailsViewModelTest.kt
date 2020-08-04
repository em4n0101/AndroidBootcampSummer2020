package com.em4n0101.mytvshows.viewmodel.showdetails

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.em4n0101.mytvshows.app.modelModule
import com.em4n0101.mytvshows.app.networkModule
import com.em4n0101.mytvshows.model.Show
import com.em4n0101.mytvshows.model.repositories.ShowsRepository
import com.em4n0101.mytvshows.viewmodel.cast.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
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
class ShowDetailsViewModelTest: KoinTest {

    private val appContext = ApplicationProvider.getApplicationContext<Context>()
    private lateinit var viewModel: ShowDetailsViewModel

    private val repository: ShowsRepository by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val mockProvider = MockProviderRule.create {
        Mockito.mock(it.java)
    }

    private val show =
        Show(1, "Game of thrones", arrayOf("drama"), "Summary demo","10-10-10", null, null, null)

    @Before
    fun setup() {
        startKoin {
            androidContext(appContext)
            modules(listOf(modelModule, networkModule))
        }
        declareMock<ShowsRepository>()
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(repository.getShowBy("Game of thrones")).thenReturn(flowOf(show))
        viewModel = ShowDetailsViewModel(repository)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun saveShow() = coroutineTestRule.testDispatcher.runBlockingTest {
        viewModel.saveShow(show)
        Mockito.verify(repository).insertShow(show)
    }

    @Test
    fun getShowByName() {
        viewModel.getShowByName("Game of thrones").observeForever {
            println(it)
            assertEquals(it?.id, 1)
        }
    }
}