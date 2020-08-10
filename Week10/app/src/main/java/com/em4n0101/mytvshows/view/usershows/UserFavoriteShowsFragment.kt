package com.em4n0101.mytvshows.view.usershows

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.Show
import com.em4n0101.mytvshows.view.searchshow.SearchShowFragment
import com.em4n0101.mytvshows.view.searchshow.ShowAdapter
import com.em4n0101.mytvshows.view.showdetail.ShowDetailActivity
import com.em4n0101.mytvshows.viewmodel.usershows.UserFavoriteShowsViewModel
import kotlinx.android.synthetic.main.fragment_user_favorite_shows.*
import org.koin.android.ext.android.inject

class UserFavoriteShowsFragment : Fragment() {
    private val viewModel: UserFavoriteShowsViewModel by inject()

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
        viewModel.getShows().observe(viewLifecycleOwner, observer)
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