package com.em4n0101.mytvshows.view.episodes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.app.SCOPE_EPISODES
import com.em4n0101.mytvshows.model.response.EpisodesForSeasonResponse
import com.em4n0101.mytvshows.model.response.SeasonsForShowResponse
import com.em4n0101.mytvshows.networking.NetworkingStatusChecker
import com.em4n0101.mytvshows.view.showdetail.ShowDetailActivity
import com.em4n0101.mytvshows.utils.*
import com.em4n0101.mytvshows.viewmodel.episodes.EpisodesViewModel
import kotlinx.android.synthetic.main.activity_episodes.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.scope.viewModel
import org.koin.core.qualifier.named

class EpisodesActivity : AppCompatActivity() {
    private var scopeEpisodes = getKoin().getOrCreateScope("scopeEpisodesId", named(SCOPE_EPISODES))
    private val viewModel: EpisodesViewModel by scopeEpisodes.viewModel(this)
    private val networkStatusChecker: NetworkingStatusChecker by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episodes)

        // Get season pass from previous activity
        val season: SeasonsForShowResponse? = intent.getParcelableExtra(ShowDetailActivity.EXTRA_SEASON)
        season?.let {
            updateUiWithSeasonDetails(it)
            getEpisodesForSeason(it)
            setupObservables()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scopeEpisodes.close()
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
            loaderAnimationSeasonPosterView)
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