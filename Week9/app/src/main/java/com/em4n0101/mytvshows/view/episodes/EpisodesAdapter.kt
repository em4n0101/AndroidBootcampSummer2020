package com.em4n0101.mytvshows.view.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.response.EpisodesForSeasonResponse

class EpisodesAdapter: RecyclerView.Adapter<EpisodesViewHolder>() {

    private val episodeList: MutableList<EpisodesForSeasonResponse> = mutableListOf()

    fun setData(episodes: List<EpisodesForSeasonResponse>) {
        episodeList.clear()
        episodeList.addAll(episodes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.episode_view_holder, parent, false)
        return EpisodesViewHolder(itemView)
    }

    override fun getItemCount() = episodeList.size

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bind(episodeList[position])
    }
}