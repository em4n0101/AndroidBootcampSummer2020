package com.em4n0101.mytvshows.ui.usercastmembers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.Person
import com.em4n0101.mytvshows.ui.showdetail.CastViewHolder

class CastMemberAdapter(private val onCastMemberClicked: (Person) -> Unit): RecyclerView.Adapter<CastViewHolder>() {

    private val castMemberList: MutableList<Person> = mutableListOf()

    fun setData(cast: List<Person>) {
        castMemberList.clear()
        castMemberList.addAll(cast)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.generic_item_big_view_holder, parent, false)
        return CastViewHolder(itemView)
    }

    override fun getItemCount() = castMemberList.size

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(castMemberList[position], onCastMemberClicked)
    }
}