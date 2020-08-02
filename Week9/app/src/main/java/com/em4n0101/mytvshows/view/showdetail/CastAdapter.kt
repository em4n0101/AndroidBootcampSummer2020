package com.em4n0101.mytvshows.view.showdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.response.CastForShowResponse

class CastAdapter(private val onCastClicked: (CastForShowResponse) -> Unit): RecyclerView.Adapter<CastViewHolder>() {

    private val castList: MutableList<CastForShowResponse> = mutableListOf()

    fun setData(cast: List<CastForShowResponse>) {
        castList.clear()
        castList.addAll(cast)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.generic_item_view_holder, parent, false)
        return CastViewHolder(itemView)
    }

    override fun getItemCount() = castList.size

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(castList[position], onCastClicked)
    }
}