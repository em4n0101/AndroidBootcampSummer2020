package com.em4n0101.mytvshows.ui.searchshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.Show

class ShowAdapter (private val onShowClicked: (Show) -> Unit): RecyclerView.Adapter<ShowViewHolder>() {

    private val showList: MutableList<Show> = mutableListOf()

    fun setData(shows: List<Show>) {
        showList.clear()
        showList.addAll(shows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.show_view_holder, parent, false)
        return ShowViewHolder(itemView)
    }

    override fun getItemCount() = showList.size

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(showList[position], onShowClicked)
    }
}