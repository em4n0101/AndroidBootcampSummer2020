package com.em4n0101.mytvshows.ui.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.response.ScheduleResponse

class ScheduleAdapter(private val onScheduleClicked: (ScheduleResponse) -> Unit): RecyclerView.Adapter<ScheduleViewHolder>() {

    private val schedule: MutableList<ScheduleResponse> = mutableListOf()

    fun setData(scheduleShows: List<ScheduleResponse>) {
        schedule.clear()
        schedule.addAll(scheduleShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.schedule_view_holder, parent, false)
        return ScheduleViewHolder(itemView)
    }

    override fun getItemCount() = schedule.size

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(schedule[position], onScheduleClicked)
    }
}