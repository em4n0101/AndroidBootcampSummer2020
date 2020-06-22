package com.em4n0101.mymovies.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mymovies.R

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title = itemView.findViewById<TextView>(R.id.movie_title)
    val poster = itemView.findViewById<ImageView>(R.id.movie_poster)
}