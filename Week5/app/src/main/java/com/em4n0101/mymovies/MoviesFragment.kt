package com.em4n0101.mymovies

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            recycler_view.adapter = MoviesAdapter(moviesManager.getListOfMovies(), this)
        }
    }

    // MoviesAdapter.SelectItemListener interface implementation
    override fun listItemPressed(movie: Movie) {
        view?.let {
            val action =  MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(movie.title)
            it.findNavController().navigate(action)
        }
    }

    companion object {
        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }
    }
}