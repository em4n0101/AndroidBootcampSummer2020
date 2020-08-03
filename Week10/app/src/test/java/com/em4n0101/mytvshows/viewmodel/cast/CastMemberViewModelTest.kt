package com.em4n0101.mytvshows.viewmodel.cast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.em4n0101.mytvshows.model.Country
import com.em4n0101.mytvshows.model.InnerImages
import com.em4n0101.mytvshows.model.Person
import com.em4n0101.mytvshows.model.database.ShowsDatabaseDao
import com.em4n0101.mytvshows.model.repositories.ShowsRepository
import com.em4n0101.mytvshows.networking.RemoteApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Test live data following this article
 * https://medium.com/androiddevelopers/unit-testing-livedata-and-other-common-observability-problems-bb477262eb04
 */
class CastMemberViewModelTest {

    private lateinit var viewModel: CastMemberViewModel
    private lateinit var repo : ShowsRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var databaseDao: ShowsDatabaseDao

    @Mock
    lateinit var remoteApi: RemoteApi

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repo = ShowsRepository(databaseDao, remoteApi)
        viewModel = CastMemberViewModel(repo)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun savePerson() = coroutineTestRule.testDispatcher.runBlockingTest {
        val person = Person(
            3,
            "Emmanuel",
            Country("Mex"),
            "10-10-10",
            InnerImages("url","url")
        )
        viewModel.savePerson(person)
        assertEquals(repo.getPersonByName("Emmanuel").asLiveData().getOrAwaitValue(), person)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getPersonByName() = runBlockingTest {
        val person = viewModel.getPersonByName("Emmanuel")
        assertNull(person)
    }
}