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

class MoviesFragment : Fragment(), MoviesAdapter.SelectItemListener {

    private lateinit var moviesManager: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            // get model
            val movieList = Utilities.createMovies(it)

            moviesManager = ViewModelProvider(this).get(MoviesViewModel::class.java)

            // setup recycler
            if (it.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                recycler_view.layoutManager = GridLayoutManager(it, 2)
            } else {
                recycler_view.layoutManager = GridLayoutManager(it, 4)
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
                recycler_view.adapter = MoviesAdapter(it, this)
            } else {
                populateDatabase()
            }
        }
        moviesManager.movies.observe(viewLifecycleOwner, observer)
    }

    private fun populateDatabase() {
        activity?.let {
            for (movie in Utilities.createMovies(it)) {
                moviesManager.saveMovie(movie)
            }
        }
    }

    // MoviesAdapter.SelectItemListener interface implementation
    override fun listItemPressed(movie: Movie) {
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