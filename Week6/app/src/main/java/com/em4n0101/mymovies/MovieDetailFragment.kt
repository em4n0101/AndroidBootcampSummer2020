package com.em4n0101.mymovies

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.em4n0101.mymovies.data.Movie
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var moviesViewModel: MoviesViewModel
    private var checkBoxFavorite: CheckBox? = null
    private var currentMovie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_details_movie, menu)

        val starMenuItem = menu.findItem(R.id.actionFavoriteMovie)
        checkBoxFavorite = starMenuItem.actionView as CheckBox
        checkBoxFavorite?.let {
            setupFavoriteToggle(it, currentMovie)
        }
    }

    private fun getMovieByTitle(movieTitle: String) {
        lifecycleScope.launch {
            moviesViewModel.getMovieByTitleFlow(movieTitle).collect { movieFound ->
                if (movieFound != null) {
                    currentMovie = movieFound
                    updateUIWith(movieFound)
                    setupFavoriteToggle(checkBoxFavorite, movieFound)
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

    private fun setupFavoriteToggle(checkBox: CheckBox?, movie: Movie?){
        if (checkBox != null && movie != null) {
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                movie.isFavorite = isChecked
                moviesViewModel.updateMovie(movie)
            }
            movie.isFavorite?.let { checkBox.isChecked = it }
        }
    }

    companion object {
        fun newInstance(): MovieDetailFragment {
            return MovieDetailFragment()
        }
    }
}