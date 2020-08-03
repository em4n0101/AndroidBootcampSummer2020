package com.em4n0101.mytvshows.app

import com.em4n0101.mytvshows.view.cast.CastMemberActivity
import com.em4n0101.mytvshows.view.episodes.EpisodesActivity
import com.em4n0101.mytvshows.view.schedule.ScheduleFragment
import com.em4n0101.mytvshows.view.searchshow.SearchShowFragment
import com.em4n0101.mytvshows.view.showdetail.ShowDetailActivity
import com.em4n0101.mytvshows.view.usercastmembers.UserFavoriteCastMemberFragment
import com.em4n0101.mytvshows.view.usershows.UserFavoriteShowsFragment
import com.em4n0101.mytvshows.viewmodel.cast.CastMemberViewModel
import com.em4n0101.mytvshows.viewmodel.episodes.EpisodesViewModel
import com.em4n0101.mytvshows.viewmodel.schedule.ScheduleViewModel
import com.em4n0101.mytvshows.viewmodel.searchshow.SearchShowViewModel
import com.em4n0101.mytvshows.viewmodel.showdetails.ShowDetailsViewModel
import com.em4n0101.mytvshows.viewmodel.usercastmember.UserFavoriteCastMemberViewModel
import com.em4n0101.mytvshows.viewmodel.usershows.UserFavoriteShowsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val SCOPE_SEARCH_SHOW = "SCOPE_SEARCH_SHOW"
const val SCOPE_SHOW_DETAILS = "SCOPE_SHOW_DETAILS"
const val SCOPE_EPISODES = "SCOPE_EPISODES"
const val SCOPE_CAST_MEMBERS = "SCOPE_CAST_MEMBERS"
const val SCOPE_SCHEDULE = "SCOPE_SCHEDULE"
const val SCOPE__FAVORITE_CAST_MEMBERS = "SCOPE__FAVORITE_CAST_MEMBERS"
const val SCOPE_FAVORITE_SHOWS = "SCOPE_FAVORITE_SHOWS"

var viewModelsModule = module {
    scope(named(SCOPE_SEARCH_SHOW)) {
         scoped {
            SearchShowFragment()
         }
         viewModel { SearchShowViewModel(get()) }
    }

    scope(named(SCOPE_SHOW_DETAILS)) {
        scoped {
            ShowDetailActivity()
        }
        viewModel { ShowDetailsViewModel(get()) }
    }

    scope(named(SCOPE_EPISODES)) {
        scoped {
            EpisodesActivity()
        }
        viewModel { EpisodesViewModel(get()) }
    }

    scope(named(SCOPE_CAST_MEMBERS)) {
        scoped {
            CastMemberActivity()
        }
        viewModel { CastMemberViewModel(get()) }
    }

    scope(named(SCOPE_SCHEDULE)) {
        scoped {
            ScheduleFragment()
        }
        viewModel { ScheduleViewModel(get()) }
    }

    scope(named(SCOPE__FAVORITE_CAST_MEMBERS)) {
        scoped {
            UserFavoriteCastMemberFragment()
        }
        viewModel { UserFavoriteCastMemberViewModel(get()) }
    }

    scope(named(SCOPE_FAVORITE_SHOWS)) {
        scoped {
            UserFavoriteShowsFragment()
        }
        viewModel { UserFavoriteShowsViewModel(get()) }
    }
}