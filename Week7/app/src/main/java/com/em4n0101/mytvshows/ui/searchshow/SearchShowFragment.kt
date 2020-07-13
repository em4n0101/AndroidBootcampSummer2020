package com.em4n0101.mytvshows.ui.searchshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.em4n0101.mytvshows.R

class SearchShowFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_show, container, false)
    }

    companion object {
        fun newInstance(param1: String, param2: String): SearchShowFragment {
            return SearchShowFragment()
        }
    }
}