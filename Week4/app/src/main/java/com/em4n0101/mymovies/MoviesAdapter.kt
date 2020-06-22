package com.em4n0101.mymovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mymovies.data.Movie
import com.em4n0101.mymovies.utils.MovieViewHolder

class MoviesAdapter(private val movieList: ArrayList<Movie>): RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val holder = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_view_holder, parent, false)
        return MovieViewHolder(holder)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.title.text = movieList[position].title
        holder.poster.setImageResource(movieList[position].posterResource)
    }
}