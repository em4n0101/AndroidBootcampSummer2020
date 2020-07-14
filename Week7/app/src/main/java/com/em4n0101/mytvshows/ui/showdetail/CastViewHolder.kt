package com.em4n0101.mytvshows.ui.showdetail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mytvshows.model.response.CastForShowResponse
import com.em4n0101.mytvshows.utils.setupImageForViewHolder
import kotlinx.android.synthetic.main.generic_item_view_holder.view.*

class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(cast: CastForShowResponse, onCastClick: (CastForShowResponse) -> Unit) {
        itemView.seasonNumber.text = cast.person.name
        itemView.setOnClickListener { onCastClick(cast) }
        setupImageForViewHolder(cast.person.image, itemView.seasonPoster, itemView.loaderAnimationView)
    }
}