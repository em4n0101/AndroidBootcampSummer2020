package com.em4n0101.mytvshows.view.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.em4n0101.mytvshows.view.schedule.ScheduleFragment
import com.em4n0101.mytvshows.view.searchshow.SearchShowFragment
import com.em4n0101.mytvshows.view.usercastmembers.UserFavoriteCastMemberFragment
import com.em4n0101.mytvshows.view.usershows.UserFavoriteShowsFragment

/**
 * Displays the pages for the main screen.
 */
class MainPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = listOf(
        ScheduleFragment(),
        SearchShowFragment(),
        UserFavoriteShowsFragment(), 
        UserFavoriteCastMemberFragment()
    )
    private val titles = listOf(
        "Schedule",
        "Search a tv show!",
        "Favorite shows",
        "Favorite actors"
    )

    override fun getCount(): Int = fragments.size
    override fun getItem(position: Int): Fragment = fragments[position]
    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}