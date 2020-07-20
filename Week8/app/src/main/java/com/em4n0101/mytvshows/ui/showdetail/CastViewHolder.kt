package com.em4n0101.mytvshows.ui.showdetail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mytvshows.model.Person
import com.em4n0101.mytvshows.model.response.CastForShowResponse
import com.em4n0101.mytvshows.utils.setupImageForViewHolder
import kotlinx.android.synthetic.main.generic_item_big_view_holder.view.*
import kotlinx.android.synthetic.main.generic_item_view_holder.view.*

class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(cast: CastForShowResponse, onCastClick: (CastForShowResponse) -> Unit) {
        itemView.itemTitle.text = cast.person.name
        itemView.setOnClickListener { onCastClick(cast) }
        setupImageForViewHolder(cast.person.image, itemView.itemPoster, itemView.loaderAnimationView)
    }

    fun bind(castMember: Person, onCastMemberClick: (Person) -> Unit) {
        itemView.itemBigTitle.text = castMember.name
        itemView.setOnClickListener { onCastMemberClick(castMember) }
        setupImageForViewHolder(castMember.image, itemView.itemBigPoster, itemView.loaderBigAnimationView)
    }
}