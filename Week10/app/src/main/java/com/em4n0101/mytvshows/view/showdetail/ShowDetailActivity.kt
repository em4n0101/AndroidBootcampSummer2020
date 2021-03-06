package com.em4n0101.mytvshows.view.showdetail

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.CompleteInfoForShow
import com.em4n0101.mytvshows.model.Show
import com.em4n0101.mytvshows.model.response.CastForShowResponse
import com.em4n0101.mytvshows.model.response.SeasonsForShowResponse
import com.em4n0101.mytvshows.networking.NetworkingStatusChecker
import com.em4n0101.mytvshows.view.cast.CastMemberActivity
import com.em4n0101.mytvshows.view.episodes.EpisodesActivity
import com.em4n0101.mytvshows.view.searchshow.SearchShowFragment
import com.em4n0101.mytvshows.utils.*
import com.em4n0101.mytvshows.viewmodel.showdetails.ShowDetailsViewModel
import kotlinx.android.synthetic.main.activity_show_detail.*
import kotlinx.android.synthetic.main.activity_show_detail.loaderAnimationView
import org.koin.android.ext.android.inject

class ShowDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PERSON = "EXTRA_PERSON"
        const val EXTRA_SEASON = "EXTRA_SEASON"
    }

    private val viewModel: ShowDetailsViewModel by inject()
    private val networkStatusChecker by lazy {
        NetworkingStatusChecker(getSystemService(ConnectivityManager::class.java))
    }
    private var currentShow: Show? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get show pass from previous activity
        val show: Show? = intent.getParcelableExtra(SearchShowFragment.EXTRA_SHOW)
        show?.let {
            collapsingToolbar.title = it.name
            collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))
            currentShow = it
            updateUiWithShowDetails(it)
            setupObservables(it)
            getCompleteInfoForShow(it)
        }

        favoriteAnimationView.setOnClickListener {
            currentShow?.let {
                isFavorite = !isFavorite
                if (isFavorite) viewModel.saveShow(it) else viewModel.deleteShowByName(it.name)
                animateFavoriteView()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finishAfterTransition();
            return true;
        }
        return false;
    }

    private fun setupObservables(forShow: Show) {
        viewModel.loadingLiveData.observe(this, Observer { value: Boolean ->
            loaderAnimationView.visibility = if (value) View.VISIBLE else View.GONE
        })
        viewModel.errorLiveData.observe(this, Observer {
            toast(getString(R.string.error_network_download_data))
        })
        viewModel.showCompleteInfoLiveData.observe(this, Observer { additionalInfo: CompleteInfoForShow ->
            updateUIWithShowInfo(currentShow, additionalInfo.seasons, additionalInfo.cast)
        })
        viewModel.getShowByName(forShow.name).observe(this, Observer {
            if (it != null) {
                isFavorite = true
                animateFavoriteView()
            }
        })
    }

    private fun animateFavoriteView() {
        currentShow?.let {
            favoriteAnimationView.apply {
                if (isFavorite) {
                    playAnimation()
                } else {
                    cancelAnimation()
                    progress = 0.0f
                }
            }
        }
    }

    private fun updateUIWithShowInfo(
        show: Show?,
        seasons: List<SeasonsForShowResponse>? = null,
        cast: List<CastForShowResponse>? = null
    ) {
        if (show != null) updateUiWithShowDetails(show)
        if (seasons != null) updateUiWithSeasons(seasons)
        if (cast != null) updateUiWithCast(cast)
    }

    private fun updateUiWithShowDetails(show: Show) {
        setupImageForViewHolder(show.image, showDetailImageView, loaderAnimationShowPosterView)
        setupImageForViewHolder(
            show.image,
            imageShowHeader,
            loaderAnimationShowHeaderPosterView,
            true
        )
        showDetailTitleTextView.text = show.name
        showDetailGenreTextView.text = show.genres?.joinToString(separator = ", ")
        val showPremiereFormatted = formatShowPremiere(show)
        showDetailPremiereDateTextView.text = showPremiereFormatted
        val showRattingFormatted = formatShowRatting(show)
        showDetailRatingTextView.text = showRattingFormatted
        showDetailSummaryTextView.text = show.summary?.removeHtmlTags()
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

    private fun updateUiWithCast(cast: List<CastForShowResponse>) {
        if (showDetailCastRecyclerView != null) {
            showDetailCastTextView.text = getString(R.string.cast_title)
            val adapter = CastAdapter(::listCastItemPressed)
            adapter.setData(cast)
            showDetailCastRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            showDetailCastRecyclerView.adapter = adapter
        }
    }

    /**
     * Go to the episodes activity
     */
    private fun listSeasonItemPressed(season: SeasonsForShowResponse, imageView: View) {
        val intent = Intent(this, EpisodesActivity::class.java)
        intent.putExtra(EXTRA_SEASON, season)

        val imagePair = Pair.create(imageView, "posterImageTransaction")
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this@ShowDetailActivity,
            imagePair
        )
        startActivity(intent, activityOptions.toBundle())
    }

    /**
     * Go to the cast member activity
     */
    private fun listCastItemPressed(cast: CastForShowResponse, imageView: View, titleView: View) {
        val intent = Intent(this, CastMemberActivity::class.java)
        intent.putExtra(EXTRA_PERSON, cast.person)
        val imagePair = Pair.create(imageView, "genericImageHolderTransaction")
        val titlePair = Pair.create(titleView, "genericTitleHolderTransaction")
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this@ShowDetailActivity,
            imagePair,
            titlePair
        )
        startActivity(intent, activityOptions.toBundle())
    }

    private fun displayNotNetworkAvailableMessage() {
        this.toast(getString(R.string.error_message_not_network_available_more_data))
    }

    /**
     * Request supplemental information about the show (list of seasons and cast)
     */
    private fun getCompleteInfoForShow(show: Show) {
        networkStatusChecker.performIfConnectedToInternet(::displayNotNetworkAvailableMessage) {
            viewModel.getCompleteInfoForShow(show)
        }
    }
}