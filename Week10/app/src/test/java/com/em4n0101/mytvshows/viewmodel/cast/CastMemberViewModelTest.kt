package com.em4n0101.mytvshows.viewmodel.cast

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.em4n0101.mytvshows.app.modelModule
import com.em4n0101.mytvshows.app.networkModule
import com.em4n0101.mytvshows.model.Country
import com.em4n0101.mytvshows.model.InnerImages
import com.em4n0101.mytvshows.model.Person
import com.em4n0101.mytvshows.model.repositories.ShowsRepository
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
class CastMemberViewModelTest: KoinTest {

    private val appContext = ApplicationProvider.getApplicationContext<Context>()
    private lateinit var viewModel: CastMemberViewModel

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

    private val person = Person(
        1,
        "emmanuel",
        Country("mex"),
        "10-10-10",
        InnerImages("url","url")
    )

    @Before
    fun setup() {
        startKoin {
            androidContext(appContext)
            modules(listOf(modelModule, networkModule))
        }
        declareMock<ShowsRepository>()
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(repository.getPersonByName("emmanuel")).thenReturn(flowOf(person))
        viewModel = CastMemberViewModel(repository)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun getPersonByName() {
        viewModel.getPersonByName("emmanuel").observeForever {person ->
            println(person)
            assertEquals(person?.id, 1)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun savePerson() = coroutineTestRule.testDispatcher.runBlockingTest {
        viewModel.savePerson(person)
        Mockito.verify(repository).insertPerson(person)
    }
}