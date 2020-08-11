package com.em4n0101.mytvshows.view.episodes

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.response.EpisodesForSeasonResponse
import com.em4n0101.mytvshows.model.response.SeasonsForShowResponse
import com.em4n0101.mytvshows.networking.NetworkingStatusChecker
import com.em4n0101.mytvshows.view.showdetail.ShowDetailActivity
import com.em4n0101.mytvshows.utils.*
import com.em4n0101.mytvshows.viewmodel.episodes.EpisodesViewModel
import kotlinx.android.synthetic.main.activity_episodes.*
import org.koin.android.ext.android.inject

class EpisodesActivity : AppCompatActivity() {
    private val viewModel: EpisodesViewModel by inject()
    private val networkStatusChecker by lazy {
        NetworkingStatusChecker(getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episodes)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get season pass from previous activity
        val season: SeasonsForShowResponse? = intent.getParcelableExtra(ShowDetailActivity.EXTRA_SEASON)
        season?.let {
            collapsingToolbar.title = getString(R.string.season_number, season.number)
            collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))

            updateUiWithSeasonDetails(it)
            getEpisodesForSeason(it)
            setupObservables()
        }
    }

    private fun displayNotNetworkAvailableMessage() {
        this.toast(getString(R.string.error_message_not_network_available_more_data))
    }

    private fun getEpisodesForSeason(season: SeasonsForShowResponse) {
        networkStatusChecker.performIfConnectedToInternet(::displayNotNetworkAvailableMessage) {
            viewModel.getEpisodesForSeason(season.id.toString())
        }
    }

    private fun setupObservables() {
        viewModel.loadingLiveData.observe(this, Observer { value: Boolean ->
            loaderAnimationView.visibility = if (value) View.VISIBLE else View.GONE
        })
        viewModel.errorLiveData.observe(this, Observer {
            toast(getString(R.string.error_network_download_data))
        })
        viewModel.episodesForSeasonLiveData.observe(this, Observer { value: List<EpisodesForSeasonResponse> ->
            updateUiWithEpisodes(value)
        })
    }

    private fun updateUiWithSeasonDetails(
        season: SeasonsForShowResponse
    ) {
        setupImageForViewHolder(
            season.image,
            seasonDetailImageView,
            loaderAnimationSeasonPosterView
        )
        setupImageForViewHolder(
            season.image,
            imageEpisodesHeader,
            loaderAnimationEpisodesHeaderPosterView,
            true
        )
        seasonDetailNumberTextView.text = getString(R.string.season_number, season.number)
        seasonDetailPremiereDateTextView.text = formatSeasonAirDate(season)
        seasonDetailSummaryTextView.text = season.summary?.removeHtmlTags()
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