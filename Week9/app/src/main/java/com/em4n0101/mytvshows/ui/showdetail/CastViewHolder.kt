package com.em4n0101.mytvshows.ui.showdetail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mytvshows.model.Person
import com.em4n0101.mytvshows.model.response.CastForShowResponse
import com.em4n0101.mytvshows.utils.setupImageForViewHolder
import kotlinx.android.synthetic.main.generic_item_big_view_holder.view.*
import kotlinx.android.synthetic.main.generic_item_view_holder.view.*

class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(cast: CastForShowResponse, onCastClick: (CastForShowResponse) -> Unit) = with(itemView) {
        itemTitle.text = cast.person.name
        setOnClickListener { onCastClick(cast) }
        setupImageForViewHolder(cast.person.image, itemPoster, loaderAnimationView)
    }

    fun bind(castMember: Person, onCastMemberClick: (Person) -> Unit) = with(itemView) {
        itemBigTitle.text = castMember.name
        setOnClickListener { onCastMemberClick(castMember) }
        setupImageForViewHolder(castMember.image, itemBigPoster, loaderBigAnimationView)
    }
}