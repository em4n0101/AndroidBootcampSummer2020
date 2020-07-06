package com.em4n0101.mymovies

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.em4n0101.mymovies.data.Movie
import com.em4n0101.mymovies.utils.Utilities
import kotlinx.android.synthetic.main.fragment_movies.*

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
            addObserver()
        }
    }

    private fun addObserver() {
        /**
         * Set the fragment as the observer for changes in the list of movies, if the list is empty
         * add the movies available to the database
         */
        val observer = Observer<List<Movie>> {
            if (it != null && it.isNotEmpty()) {
                val adapter = MoviesAdapter(::listItemPressed)
                adapter.setData(it)
                recyclerViewMovies.adapter = adapter
            } else {
                populateDatabase()
            }
        }
        moviesViewModel.movies.observe(viewLifecycleOwner, observer)
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
        inflater.inflate(R.menu.menu, menu)
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