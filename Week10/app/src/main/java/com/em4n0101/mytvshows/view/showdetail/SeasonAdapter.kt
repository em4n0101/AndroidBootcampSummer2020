package com.em4n0101.mytvshows.view.showdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.response.SeasonsForShowResponse

class SeasonAdapter(private val onSeasonClicked: (SeasonsForShowResponse) -> Unit): RecyclerView.Adapter<SeasonViewHolder>() {

    private val seasonsList: MutableList<SeasonsForShowResponse> = mutableListOf()

    fun setData(seasons: List<SeasonsForShowResponse>) {
        seasonsList.clear()
        seasonsList.addAll(seasons)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.generic_item_view_holder, parent, false)
        return SeasonViewHolder(itemView)
    }

    override fun getItemCount() = seasonsList.size

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        holder.bind(seasonsList[position], onSeasonClicked)
    }
}