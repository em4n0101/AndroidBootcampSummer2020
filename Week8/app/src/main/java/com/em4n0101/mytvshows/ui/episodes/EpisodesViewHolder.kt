package com.em4n0101.mytvshows.ui.episodes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mytvshows.model.response.EpisodesForSeasonResponse
import com.em4n0101.mytvshows.utils.formatEpisodeAirDate
import com.em4n0101.mytvshows.utils.removeHtmlTags
import com.em4n0101.mytvshows.utils.setupImageForViewHolder
import kotlinx.android.synthetic.main.episode_view_holder.view.*

class EpisodesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(episode: EpisodesForSeasonResponse) = with(itemView) {
        episodeTitle.text = episode.name
        episodeAirDateTextView.text = formatEpisodeAirDate(episode)
        episodeSummaryTextView.text = episode.summary?.removeHtmlTags()
        setupImageForViewHolder(
            episode.image,
            episodePoster,
            loaderAnimationView
        )
    }
}