package com.em4n0101.mytvshows.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.em4n0101.mytvshows.ui.searchshow.SearchShowFragment
import com.em4n0101.mytvshows.ui.usercastmembers.UserFavoriteCastMemberFragment
import com.em4n0101.mytvshows.ui.usershows.UserFavoriteShowsFragment

/**
 * Displays the pages for the main screen.
 */
class MainPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = listOf(
        SearchShowFragment(),
        UserFavoriteShowsFragment(), 
        UserFavoriteCastMemberFragment()
    )
    private val titles = listOf(
        "Search a tv show!",
        "Favorite shows",
        "Favorite cast members"
    )

    override fun getCount(): Int = fragments.size
    override fun getItem(position: Int): Fragment = fragments[position]
    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}