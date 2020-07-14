package com.em4n0101.mytvshows.ui.searchshow

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.response.InnerImages
import com.em4n0101.mytvshows.model.response.Show
import com.em4n0101.mytvshows.utils.convertUrlStringFromHttpToHttps
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.show_view_holder.view.*

class ShowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(show: Show, onShowClick: (Show) -> Unit) {
        itemView.showTitle.text = show.name
        setupImage(show.image)
        itemView.setOnClickListener { onShowClick(show) }
    }

    private fun setupImage(images: InnerImages?) {
        if (images != null) {
            Picasso.get()
                .load(images.medium.convertUrlStringFromHttpToHttps())
                .error(R.drawable.no_image_available)
                .into(itemView.showPoster)
        } else {
            itemView.loaderAnimationView.visibility = View.GONE
            itemView.showPoster.setImageResource(R.drawable.no_image_available)
        }
    }
}