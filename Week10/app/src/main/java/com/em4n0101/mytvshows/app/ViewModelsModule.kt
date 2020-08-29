package com.em4n0101.mytvshows.app

import com.em4n0101.mytvshows.viewmodel.cast.CastMemberViewModel
import com.em4n0101.mytvshows.viewmodel.episodes.EpisodesViewModel
import com.em4n0101.mytvshows.viewmodel.schedule.ScheduleViewModel
import com.em4n0101.mytvshows.viewmodel.searchshow.SearchShowViewModel
import com.em4n0101.mytvshows.viewmodel.showdetails.ShowDetailsViewModel
import com.em4n0101.mytvshows.viewmodel.usercastmember.UserFavoriteCastMemberViewModel
import com.em4n0101.mytvshows.viewmodel.usershows.UserFavoriteShowsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { SearchShowViewModel(get()) }
    viewModel { ShowDetailsViewModel(get()) }
    viewModel { EpisodesViewModel(get()) }
    viewModel { CastMemberViewModel(get()) }
    viewModel { ScheduleViewModel(get()) }
    viewModel { UserFavoriteCastMemberViewModel(get()) }
    viewModel { UserFavoriteShowsViewModel(get()) }
}