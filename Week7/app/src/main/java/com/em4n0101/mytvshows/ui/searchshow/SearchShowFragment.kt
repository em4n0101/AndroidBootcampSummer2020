package com.em4n0101.mytvshows.ui.searchshow

import android.content.Intent
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.em4n0101.mytvshows.MyTvShowsApplication
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.Success
import com.em4n0101.mytvshows.model.Show
import com.em4n0101.mytvshows.networking.NetworkingStatusChecker
import com.em4n0101.mytvshows.ui.showdetail.ShowDetailActivity
import com.em4n0101.mytvshows.utils.toast
import kotlinx.android.synthetic.main.fragment_search_show.*
import kotlinx.coroutines.launch

class SearchShowFragment : Fragment() {

    private val remoteApi = MyTvShowsApplication.remoteApi
    private val networkStatusChecker by lazy {
        NetworkingStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_show, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            // setup recycler
            if (context?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
                showsRecyclerView.layoutManager = GridLayoutManager(context, 2)
            } else {
                showsRecyclerView.layoutManager = GridLayoutManager(context, 4)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.actionSearchShow)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            val editText = searchView.findViewById<EditText>(R.id.search_src_text)
            editText.hint = getString(R.string.hintSearchShow)

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                   query?.let {
                        if (it.isNotBlank()) searchFor(it)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    val minCharactersToSearch = 3
                    newText?.let {
                        if (it.length >= minCharactersToSearch) {
                            searchFor(it)
                        }
                        else {
                            updateUiWithShowList(emptyList())
                        }
                    }
                    return true
                }
            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun displayNotNetworkAvailableMessage() {
        activity?.toast(getString(R.string.error_message_not_network_available_search))
    }

    private fun searchFor(inputToSearch: String) {
        networkStatusChecker.performIfConnectedToInternet(::displayNotNetworkAvailableMessage) {
            loaderAnimationView.visibility = View.VISIBLE
            emptyShowsTextView.visibility = View.GONE

            lifecycleScope.launch {
                val searchShowsResult = remoteApi.searchForAShow(inputToSearch)
                loaderAnimationView.visibility = View.GONE

                if (searchShowsResult is Success) {
                    val shows = mutableListOf<Show>()
                    searchShowsResult.data.forEach {
                        shows.add(it.show)
                    }
                    updateUiWithShowList(shows.toList())
                } else {
                    activity?.toast(getString(R.string.error_network_download_data))
                }
            }
        }
    }

    private fun updateUiWithShowList(listOfShows: List<Show>) {
        emptyShowsTextView.visibility = if (listOfShows.isEmpty()) View.VISIBLE else View.GONE
        if (showsRecyclerView != null) {
            val adapter = ShowAdapter(::listItemPressed)
            adapter.setData(listOfShows)
            showsRecyclerView.adapter = adapter
        }
    }

    private fun listItemPressed(show: Show) {
        view?.let {
            val intent = Intent(context, ShowDetailActivity::class.java)
            intent.putExtra(EXTRA_SHOW, show)
            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_SHOW = "EXTRA_SHOW"

        fun newInstance(param1: String, param2: String): SearchShowFragment {
            return SearchShowFragment()
        }
    }
}