package com.em4n0101.mytvshows.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.em4n0101.mytvshows.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val pagerAdapter by lazy { MainPagerAdapter(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
    }

    private fun initUi() {
        tabs.setupWithViewPager(fragmentPager)
        fragmentPager.adapter = pagerAdapter
    }
}