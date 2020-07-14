package com.em4n0101.mytvshows.ui.showdetail

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.em4n0101.mytvshows.MyTvShowsApplication
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.Failure
import com.em4n0101.mytvshows.model.Show
import com.em4n0101.mytvshows.model.Success
import com.em4n0101.mytvshows.model.response.CastForShowResponse
import com.em4n0101.mytvshows.model.response.SeasonsForShowResponse
import com.em4n0101.mytvshows.networking.NetworkingStatusChecker
import com.em4n0101.mytvshows.ui.searchshow.SearchShowFragment
import com.em4n0101.mytvshows.utils.formatShowPremiere
import com.em4n0101.mytvshows.utils.formatShowRatting
import com.em4n0101.mytvshows.utils.removeHtmlTags
import com.em4n0101.mytvshows.utils.setupImageForViewHolder
import com.em4n0101.mytvshows.viewModels.ShowsViewModel
import kotlinx.android.synthetic.main.activity_show_detail.*
import kotlinx.coroutines.launch


class ShowDetailActivity : AppCompatActivity() {

    private val remoteApi = MyTvShowsApplication.remoteApi
    private val networkStatusChecker by lazy {
        NetworkingStatusChecker(getSystemService(ConnectivityManager::class.java))
    }
    private var checkBoxFavorite: CheckBox? = null
    private lateinit var showsViewModel: ShowsViewModel
    private var currentShow: Show? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)

        // get view model
        showsViewModel = ViewModelProvider(this).get(ShowsViewModel::class.java)

        val show: Show? = intent.getParcelableExtra(SearchShowFragment.EXTRA_SHOW)
        show?.let {
            currentShow = it
            getCompleteInfoForShow(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.show_detail_menu, menu)

        val starMenuItem = menu?.findItem(R.id.actionFavoriteShow)
        checkBoxFavorite = starMenuItem?.actionView as CheckBox
        checkBoxFavorite?.let {
            setupFavoriteToggle(it, currentShow)
        }

        return true
    }

    private fun setupFavoriteToggle(checkBox: CheckBox?, show: Show?){
        if (checkBox != null && show != null) {
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                showsViewModel.saveShow(show)
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

    private fun listSeasonItemPressed(season: SeasonsForShowResponse) {
        println("Season pressed ${season.number}")
    }

    private fun listCastItemPressed(cast: CastForShowResponse) {
        println("Cast pressed ${cast.person.name}")
    }

    private fun getCompleteInfoForShow(show: Show) {
        networkStatusChecker.performIfConnectedTooInternet {
            loaderAnimationView.visibility = View.VISIBLE
            lifecycleScope.launch {
                val getInfoForShowResponse = remoteApi.getCompleteInfoForShow(show.id.toString())
                loaderAnimationView.visibility = View.GONE

                if (getInfoForShowResponse is Success) {
                    updateUIWithShowInfo(show, getInfoForShowResponse.data.seasons, getInfoForShowResponse.data.cast)
                } else {
                    val failure = getInfoForShowResponse as Failure
                    println("Error: ${failure.error}")
                }
            }
        }
    }
}