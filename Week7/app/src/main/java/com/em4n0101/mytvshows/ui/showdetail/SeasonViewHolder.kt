package com.em4n0101.mytvshows.ui.showdetail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mytvshows.model.response.SeasonsForShowResponse
import com.em4n0101.mytvshows.utils.setupImageForViewHolder
import kotlinx.android.synthetic.main.generic_item_view_holder.view.*

class SeasonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(season: SeasonsForShowResponse, onSeasonClick: (SeasonsForShowResponse) -> Unit) {
        itemView.seasonNumber.text = season.number.toString()
        itemView.setOnClickListener { onSeasonClick(season) }
        setupImageForViewHolder(season.image, itemView.seasonPoster, itemView.loaderAnimationView)
    }
}