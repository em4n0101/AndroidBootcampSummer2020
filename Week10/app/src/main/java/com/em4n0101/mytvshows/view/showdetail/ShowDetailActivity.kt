package com.em4n0101.mytvshows.view.showdetail

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.app.SCOPE_SHOW_DETAILS
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
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.scope.viewModel
import org.koin.core.qualifier.named


class ShowDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PERSON = "EXTRA_PERSON"
        const val EXTRA_SEASON = "EXTRA_SEASON"
    }

    private var scopeShowDetails = getKoin().getOrCreateScope("scopeShowDetailsId", named(SCOPE_SHOW_DETAILS))
    private val viewModel: ShowDetailsViewModel by scopeShowDetails.viewModel(this)
    private val networkStatusChecker by lazy {
        NetworkingStatusChecker(getSystemService(ConnectivityManager::class.java))
    }
    private var checkBoxFavorite: CheckBox? = null
    private var currentShow: Show? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)

        // Get show pass from previous activity
        val show: Show? = intent.getParcelableExtra(SearchShowFragment.EXTRA_SHOW)
        show?.let {
            currentShow = it
            updateUiWithShowDetails(it)
            setupObservables(it)
            getCompleteInfoForShow(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scopeShowDetails.close()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.show_detail_menu, menu)

        val starMenuItem = menu?.findItem(R.id.actionFavoriteItem)
        checkBoxFavorite = starMenuItem?.actionView as CheckBox
        checkBoxFavorite?.let {
            if (currentShow != null) {
                setupFavoriteToggle(it, currentShow)
                setupObservables(currentShow!!)
            }
        }

        return true
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
            if (it != null) checkBoxFavorite?.isChecked = true
        })
    }

    /**
     * Depending on the state of the checkbox save or delete person
     */
    private fun setupFavoriteToggle(checkBox: CheckBox?, show: Show?){
        if (checkBox != null && show != null) {
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.saveShow(show)
                } else {
                    viewModel.deleteShowByName(show.name)
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
        showDetailTitleTextView.text = show.name
        showDetailGenreTextView.text = show.genres?.joinToString(separator = ", ")
        val showPremiereFormatted = formatShowPremiere(show)
        showDetailPremiereDateTextView.text = showPremiereFormatted
        val showRattingFormatted = formatShowRatting(show)
        showDetailRatingTextView.text = showRattingFormatted
        showDetailSummaryTextView.text = show.summary?.removeHtmlTags()

//        addSearchShowInDBObservable(show)
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
    private fun listSeasonItemPressed(season: SeasonsForShowResponse) {
        val intent = Intent(this, EpisodesActivity::class.java)
        intent.putExtra(EXTRA_SEASON, season)
        startActivity(intent)
    }

    /**
     * Go to the cast member activity
     */
    private fun listCastItemPressed(cast: CastForShowResponse) {
        val intent = Intent(this, CastMemberActivity::class.java)
        intent.putExtra(EXTRA_PERSON, cast.person)
        startActivity(intent)
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