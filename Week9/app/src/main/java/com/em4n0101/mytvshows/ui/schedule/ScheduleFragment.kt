package com.em4n0101.mytvshows.ui.schedule

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.model.response.ScheduleResponse
import com.em4n0101.mytvshows.ui.searchshow.SearchShowFragment
import com.em4n0101.mytvshows.ui.showdetail.ShowDetailActivity
import com.em4n0101.mytvshows.viewModels.ShowsViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleFragment : Fragment() {
    private lateinit var showsViewModel: ShowsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            showsViewModel = ViewModelProvider(this).get(ShowsViewModel::class.java)
            scheduleRecyclerView.layoutManager = LinearLayoutManager(context)
            addGetScheduleObservable()
        }
    }

    private fun addGetScheduleObservable() {
        val observer = Observer<List<ScheduleResponse>> {
            if (it != null) {
                updateUiWithSchedule(it)
            }
        }
        showsViewModel.getSchedule().observe(viewLifecycleOwner, observer)
    }

    private fun updateUiWithSchedule(schedule: List<ScheduleResponse>) {
        if (scheduleRecyclerView != null) {
            val adapter = ScheduleAdapter(::listItemPressed)
            adapter.setData(schedule)
            scheduleRecyclerView.adapter = adapter
        }
    }

    private fun listItemPressed(schedule: ScheduleResponse) {
        view?.let {
            val intent = Intent(context, ShowDetailActivity::class.java)
            intent.putExtra(SearchShowFragment.EXTRA_SHOW, schedule.show)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance(): ScheduleFragment {
            return ScheduleFragment()
        }

    }
}