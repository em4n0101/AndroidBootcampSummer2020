package com.em4n0101.mymovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.em4n0101.mymovies.data.Movie
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var moviesViewModel: MoviesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            // get the title of the movie to display
            val args = MovieDetailFragmentArgs.fromBundle(it)

            // get view model
            moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

            getMovieByTitle(args.movieTitleString)
        }
    }

    private fun getMovieByTitle(movieTitle: String) {
        lifecycleScope.launch {
            moviesViewModel.getMovieByTitleFlow(movieTitle).collect { movie ->
                if (movie != null) {
                    updateUIWith(movie)
                }
            }
        }
    }

    private fun updateUIWith(movie: Movie) {
        activity?.let {
            detailMoviePoster.setImageResource(movie.posterResource)
            detailMovieTitle.text = movie.title
            detailMovieReleaseDate.text = movie.releaseDate
            detailMovieGenre.text = movie.genre
            detailMovieDuration.text = movie.duration
            detailMovieSummary.text = movie.summary
            it.title = movie.title
        }
    }

    companion object {
        fun newInstance(): MovieDetailFragment {
            return MovieDetailFragment()
        }
    }
}