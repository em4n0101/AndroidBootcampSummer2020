package com.em4n0101.mymovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mymovies.data.Movie
import com.em4n0101.mymovies.utils.MovieViewHolder

class MoviesAdapter(private val onMovieClicked: (Movie) -> Unit): RecyclerView.Adapter<MovieViewHolder>() {

    private val movieList: MutableList<Movie> = mutableListOf()

    fun setData(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_view_holder, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position], onMovieClicked)
    }
}