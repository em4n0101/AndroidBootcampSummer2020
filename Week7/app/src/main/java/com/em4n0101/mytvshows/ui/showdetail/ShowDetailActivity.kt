package com.em4n0101.mytvshows.ui.showdetail

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
import com.em4n0101.mytvshows.model.response.SeasonsForShowResponse
import com.em4n0101.mytvshows.model.response.Show
import com.em4n0101.mytvshows.networking.NetworkingStatusChecker
import com.em4n0101.mytvshows.ui.searchshow.SearchShowFragment
import com.em4n0101.mytvshows.utils.formatTimeToReadableText
import com.em4n0101.mytvshows.utils.removeHtmlTags
import com.em4n0101.mytvshows.utils.setupImageForViewHolder
import kotlinx.android.synthetic.main.activity_show_detail.*
import kotlinx.coroutines.launch

class ShowDetailActivity : AppCompatActivity() {

    private val remoteApi = MyTvShowsApplication.remoteApi
    private val networkStatusChecker by lazy {
        NetworkingStatusChecker(getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)

        val show: Show? = intent.getParcelableExtra(SearchShowFragment.EXTRA_SHOW)
        show?.let {
            getCompleteInfoForShow(it)
        }
    }

    private fun updateUIWithShowInfo(show: Show?, seasons: List<SeasonsForShowResponse>? = null) {
        if (show != null) {
            setupImageForViewHolder(show.image, showDetailImageView, loaderAnimationShowPosterView)
            showDetailTitleTextView.text = show.name
            showDetailGenreTextView.text = show.genres?.joinToString(separator = ", ")
            showDetailPremiereDateTextView.text = if (show.premiered != null) "Release Date: ${formatTimeToReadableText(show.premiered)}" else ""
            showDetailRatingTextView.text = if (show.rating?.average != null) "Rating: ${show.rating.average}" else ""
            showDetailSummaryTextView.text = show.summary?.removeHtmlTags()
        }

        if (seasons != null) {
            updateUiWithSeasons(seasons)
        }
    }

    private fun updateUiWithSeasons(seasons: List<SeasonsForShowResponse>) {
        if (showDetailSeasonsRecyclerView != null) {
            showDetailSeasonsTextView.text = getString(R.string.seasons_title)
            val adapter = SeasonAdapter(::listSeasonItemPressed)
            adapter.setData(seasons)
            showDetailSeasonsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            showDetailSeasonsRecyclerView.adapter = adapter
        }
    }

    private fun listSeasonItemPressed(season: SeasonsForShowResponse) {
        println("Season pressed ${season.number}")
    }

    private fun getCompleteInfoForShow(show: Show) {
        networkStatusChecker.performIfConnectedTooInternet {
            loaderAnimationView.visibility = View.VISIBLE
            lifecycleScope.launch {
                val getSeasonsForShowsResult = remoteApi.getSeasonsForShow(show.id.toString())
                loaderAnimationView.visibility = View.GONE

                if (getSeasonsForShowsResult is Success) {
                    updateUIWithShowInfo(show, getSeasonsForShowsResult.data)
                } else {
                    val failure = getSeasonsForShowsResult as Failure
                    println("Error: ${failure.error}")
                }
            }
        }
    }
}