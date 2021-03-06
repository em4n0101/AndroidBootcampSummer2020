package com.em4n0101.mymovies.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mymovies.R
import com.em4n0101.mymovies.data.Movie
import kotlinx.android.synthetic.main.movie_view_holder.view.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie, onMovieClick: (Movie) -> Unit) {
        itemView.movieTitle.text = movie.title
        itemView.moviePoster.setImageResource(movie.posterResource)
        itemView.setOnClickListener { onMovieClick(movie) }
        val resourceId = if (movie.isFavorite != null && movie.isFavorite!!) {
            R.drawable.ic_star
        } else {
            R.drawable.ic_star_border
        }
        itemView.favoriteImageView.setImageResource(resourceId)
    }
}