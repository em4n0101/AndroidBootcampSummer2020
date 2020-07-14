package com.em4n0101.mytvshows.ui.searchshow

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mytvshows.model.Show
import com.em4n0101.mytvshows.utils.setupImageForViewHolder
import kotlinx.android.synthetic.main.show_view_holder.view.*

class ShowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(show: Show, onShowClick: (Show) -> Unit) {
        itemView.showTitle.text = show.name
        itemView.setOnClickListener { onShowClick(show) }
        setupImageForViewHolder(show.image, itemView.showPoster, itemView.loaderAnimationView)
    }
}