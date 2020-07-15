package com.em4n0101.mytvshows.ui.episodes

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.em4n0101.mytvshows.MyTvShowsApplication
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.Failure
import com.em4n0101.mytvshows.model.Success
import com.em4n0101.mytvshows.model.response.EpisodesForSeasonResponse
import com.em4n0101.mytvshows.model.response.SeasonsForShowResponse
import com.em4n0101.mytvshows.networking.NetworkingStatusChecker
import com.em4n0101.mytvshows.ui.showdetail.ShowDetailActivity
import com.em4n0101.mytvshows.utils.*
import kotlinx.android.synthetic.main.activity_episodes.*
import kotlinx.coroutines.launch

class EpisodesActivity : AppCompatActivity() {

    private val remoteApi = MyTvShowsApplication.remoteApi
    private val networkStatusChecker by lazy {
        NetworkingStatusChecker(getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episodes)

        // Get season pass from previous activity
        val season: SeasonsForShowResponse? = intent.getParcelableExtra(ShowDetailActivity.EXTRA_SEASON)
        season?.let {
            getEpisodesForSeason(it)
        }
    }

    private fun displayNotNetworkAvailableMessage() {
        this.toast(getString(R.string.error_message_not_network_available_more_data))
    }

    private fun getEpisodesForSeason(season: SeasonsForShowResponse) {
        networkStatusChecker.performIfConnectedToInternet(::displayNotNetworkAvailableMessage) {
            loaderAnimationView.visibility = View.VISIBLE
            lifecycleScope.launch {
                val getEpisodesForSeasonResponse = remoteApi.getEpisodesForSeason(season.id.toString())
                loaderAnimationView.visibility = View.GONE

                if (getEpisodesForSeasonResponse is Success) {
                    updateUiWithSeasonDetails(season, getEpisodesForSeasonResponse.data)
                } else {
                    updateUiWithSeasonDetails(season, null)
                    toast(getString(R.string.error_network_download_data))
                }
            }
        }
    }

    private fun updateUiWithSeasonDetails(
        season: SeasonsForShowResponse,
        episodes: List<EpisodesForSeasonResponse>? = null
    ) {
        setupImageForViewHolder(
            season.image,
            seasonDetailImageView,
            loaderAnimationSeasonPosterView)
        seasonDetailNumberTextView.text = getString(R.string.season_number, season.number)
        seasonDetailPremiereDateTextView.text = formatSeasonAirDate(season)
        seasonDetailSummaryTextView.text = season.summary?.removeHtmlTags()
        if (episodes != null) updateUiWithEpisodes(episodes)
    }

    private fun updateUiWithEpisodes(episodes: List<EpisodesForSeasonResponse>) {
        if (seasonDetailEpisodesRecyclerView != null) {
            seasonDetailEpisodesTextView.text = getString(R.string.episodes_title)
            val adapter = EpisodesAdapter()
            adapter.setData(episodes)
            seasonDetailEpisodesRecyclerView.layoutManager = LinearLayoutManager(this)
            seasonDetailEpisodesRecyclerView.adapter = adapter
        }
    }
}