package com.em4n0101.mymovies

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.em4n0101.mymovies.data.Movie
import com.em4n0101.mymovies.utils.Utilities
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            // get model
            val movieList = Utilities.createMovies(it)

            moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

            // setup recycler
            if (it.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                recyclerViewMovies.layoutManager = GridLayoutManager(it, 2)
            } else {
                recyclerViewMovies.layoutManager = GridLayoutManager(it, 4)
            }

            getListOfMovies()
        }
    }

    private fun getListOfMovies() {
        lifecycleScope.launch {
            moviesViewModel.getMoviesFlow().collect { listOfMovies ->
                if (listOfMovies.isNotEmpty()) {
                    updateUiWithMovieList(listOfMovies)
                } else {
                    populateDatabase()
                }
            }
        }
    }

    private fun updateUiWithMovieList(listOfMovies: List<Movie>) {
        if (recyclerViewMovies != null) {
            val adapter = MoviesAdapter(::listItemPressed)
            adapter.setData(listOfMovies)
            recyclerViewMovies.adapter = adapter
        }
    }

    private fun populateDatabase() {
        activity?.let {
            moviesViewModel.saveMovies(Utilities.createMovies(it))
        }
    }

    private fun listItemPressed(movie: Movie) {
        view?.let {
            val action =  MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(movie.title)
            it.findNavController().navigate(action)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_movies, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_go_to_profile) {
            view?.let {
                val action =  MoviesFragmentDirections.actionMoviesFragmentToProfileFragment()
                it.findNavController().navigate(action)
            }
        }
        return true
    }

    companion object {
        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }
    }
}