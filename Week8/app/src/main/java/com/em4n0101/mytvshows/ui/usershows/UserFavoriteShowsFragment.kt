package com.em4n0101.mytvshows.ui.usershows

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.Show
import com.em4n0101.mytvshows.ui.searchshow.SearchShowFragment
import com.em4n0101.mytvshows.ui.searchshow.ShowAdapter
import com.em4n0101.mytvshows.ui.showdetail.ShowDetailActivity
import com.em4n0101.mytvshows.viewModels.ShowsViewModel
import kotlinx.android.synthetic.main.fragment_user_favorite_shows.*

class UserFavoriteShowsFragment : Fragment() {
    private lateinit var showsViewModel: ShowsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_favorite_shows, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            // get view model
            showsViewModel = ViewModelProvider(this).get(ShowsViewModel::class.java)

            // setup recycler
            if (it.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                favoriteShowsRecyclerView.layoutManager = GridLayoutManager(it, 2)
            } else {
                favoriteShowsRecyclerView.layoutManager = GridLayoutManager(it, 4)
            }

            addGetShowsObservable()
        }
    }

    private fun addGetShowsObservable() {
        val observer = Observer<List<Show>> {
            if (it != null) {
                updateUiWithShowList(it)
            }
        }
        showsViewModel.getShows().observe(viewLifecycleOwner, observer)
    }

    private fun updateUiWithShowList(listOfShows: List<Show>) {
        emptyShowsTextView.visibility = if (listOfShows.isEmpty()) View.VISIBLE else View.GONE
        if (favoriteShowsRecyclerView != null) {
            val adapter = ShowAdapter(::listItemPressed)
            adapter.setData(listOfShows)
            favoriteShowsRecyclerView.adapter = adapter
        }
    }

    private fun listItemPressed(show: Show) {
        view?.let {
            val intent = Intent(context, ShowDetailActivity::class.java)
            intent.putExtra(SearchShowFragment.EXTRA_SHOW, show)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance(): UserFavoriteShowsFragment {
            return UserFavoriteShowsFragment()
        }
    }
}