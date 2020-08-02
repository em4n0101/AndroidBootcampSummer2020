package com.em4n0101.mytvshows.view.schedule

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mytvshows.model.response.ScheduleResponse
import com.em4n0101.mytvshows.utils.setupImageForViewHolder
import kotlinx.android.synthetic.main.schedule_view_holder.view.*

class ScheduleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(schedule: ScheduleResponse, onScheduleClick: (ScheduleResponse) -> Unit) = with(itemView) {
        scheduleTimeAndNetworkTextView.text =
            schedule.airtime.toString() + " \n " + schedule.show?.network?.name
        scheduleShowNameTextView.text = schedule.show?.name
        scheduleEpisodeNameTextView.text = schedule.name
        setOnClickListener { onScheduleClick(schedule) }
        setupImageForViewHolder(schedule.show?.image, schedulePoster, loaderScheduleShowAnimationView)
    }
}