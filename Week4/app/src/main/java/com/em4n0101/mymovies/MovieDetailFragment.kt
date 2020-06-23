package com.em4n0101.mymovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.em4n0101.mymovies.data.Movie
import com.em4n0101.mymovies.utils.Utilities
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment() {

    private lateinit var moviesManager: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            // get the title of the movie to display
            val args = MovieDetailFragmentArgs.fromBundle(it)

            // get view model
            moviesManager = ViewModelProvider(this).get(MoviesViewModel::class.java)
            val movie = moviesManager.getListOfMovies().filter { movie -> movie.title == args.movieTitleString }[0]
            updateUIWith(movie)
        }
    }

    private fun updateUIWith(movie: Movie) {
        activity?.let {
            detail_movie_poster.setImageResource(movie.posterResource)
            detail_movie_title.text = movie.title
            detail_movie_release_date.text = movie.releaseDate
            detail_movie_genre.text = movie.genre
            detail_movie_duration.text = movie.duration
            detail_movie_summary.text = movie.summary
            it.title = movie.title
        }
    }

    companion object {
        fun newInstance(): MovieDetailFragment {
            return MovieDetailFragment()
        }
    }
}