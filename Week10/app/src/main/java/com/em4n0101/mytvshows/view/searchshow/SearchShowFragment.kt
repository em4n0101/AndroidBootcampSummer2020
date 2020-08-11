package com.em4n0101.mytvshows.view.searchshow

import android.content.Intent
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.Show
import com.em4n0101.mytvshows.networking.NetworkingStatusChecker
import com.em4n0101.mytvshows.view.showdetail.ShowDetailActivity
import com.em4n0101.mytvshows.utils.toast
import com.em4n0101.mytvshows.viewmodel.searchshow.SearchShowViewModel
import kotlinx.android.synthetic.main.fragment_search_show.*
import kotlinx.android.synthetic.main.fragment_search_show.loaderAnimationView
import org.koin.android.ext.android.inject

class SearchShowFragment : Fragment() {
    private val viewModel: SearchShowViewModel by inject()
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
            setupObservables()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.actionSearchShow)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            val editText = searchView.findViewById<EditText>(R.id.search_src_text)
            editText.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            editText.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            editText.hint = getString(R.string.hintSearchShow)

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = handlerOnQueryTextSubmit(query)

                override fun onQueryTextChange(newText: String?): Boolean = handlerOnQueryTextChange(newText)
            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun handlerOnQueryTextSubmit(query: String?): Boolean {
        query?.let {
            if (it.isNotBlank()) searchFor(it)
        }
        return true
    }

    private fun handlerOnQueryTextChange(newText: String?): Boolean {
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

    private fun displayNotNetworkAvailableMessage() {
        activity?.toast(getString(R.string.error_message_not_network_available_search))
    }

    private fun searchFor(inputToSearch: String) {
        networkStatusChecker.performIfConnectedToInternet(::displayNotNetworkAvailableMessage) {
            loaderAnimationView.visibility = View.VISIBLE
            emptyShowsTextView.visibility = View.GONE
            viewModel.searchForAShow(inputToSearch)
        }
    }

    private fun setupObservables() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner, Observer { value: Boolean ->
            loaderAnimationView.isVisible = value
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            activity?.toast(getString(R.string.error_network_download_data))
        })
        viewModel.showsFoundedLiveData.observe(viewLifecycleOwner, Observer { value: List<Show> ->
            updateUiWithShowList(value)
        })
    }

    private fun updateUiWithShowList(listOfShows: List<Show>) {
        emptyShowsTextView.isVisible = listOfShows.isEmpty()
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
    }
}