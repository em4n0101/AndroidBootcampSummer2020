package com.em4n0101.mytvshows.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.utils.DepthPageTransformer
import com.em4n0101.mytvshows.workers.DownloadScheduleWorker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val pagerAdapter by lazy { MainPagerAdapter(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initUi()
    }

    private fun initUi() {
        tabs.setupWithViewPager(fragmentPager)
        fragmentPager.adapter = pagerAdapter
        fragmentPager.setPageTransformer(true, DepthPageTransformer())

        synchronizedSchedule()
    }

    private fun synchronizedSchedule() {
        val constraint = Constraints.Builder()
            .setRequiresStorageNotLow(true)
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val getShowReceiverWorker =
            PeriodicWorkRequestBuilder<DownloadScheduleWorker>(1, TimeUnit.HOURS)
                .setConstraints(constraint)
                .build()

        val workerManager = WorkManager.getInstance(this)
        workerManager.enqueueUniquePeriodicWork(
            DownloadScheduleWorker::class.java.name,
            ExistingPeriodicWorkPolicy.REPLACE,
            getShowReceiverWorker
        )
    }
}