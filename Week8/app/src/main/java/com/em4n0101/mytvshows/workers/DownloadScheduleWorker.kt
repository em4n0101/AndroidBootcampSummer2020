package com.em4n0101.mytvshows.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.em4n0101.mytvshows.MyTvShowsApplication
import com.em4n0101.mytvshows.R
import com.em4n0101.mytvshows.database.ShowsDatabase
import com.em4n0101.mytvshows.model.Success
import com.em4n0101.mytvshows.utils.toast

const val NOTIFICATION_CHANNEL_NAME = "Synchronize service channel"
const val NOTIFICATION_CHANNEL_ID = "Synchronize ID"

class DownloadScheduleWorker(context: Context, workerParameters: WorkerParameters):
    CoroutineWorker(context, workerParameters) {

    private val databaseDao by lazy { ShowsDatabase.getInstance(context).showsDatabaseDao }
    private val remoteApi by lazy { MyTvShowsApplication.remoteApi }

    override suspend fun doWork(): Result {
        showNotification("Synchronization Service", "Downloading schedule...")

        val result = remoteApi.getCurrentSchedule()
        return if (result is Success) {
            databaseDao.deleteSchedule()
            databaseDao.insertSchedule(result.data)
            Result.success()
        } else {
            MyTvShowsApplication.getAppContext()
                .toast("Something gone wrong while synchronizing the schedule")
            Result.failure()
        }
    }

    private fun showNotification(title: String, message: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(notificationManager)
        val notification: NotificationCompat.Builder = NotificationCompat.Builder(
            applicationContext,
            "default"
        )
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.mipmap.ic_launcher)
        notificationManager.notify(1, notification.build())
    }

    private fun createNotificationChannel(notificationManager: NotificationManager?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH)
            notificationManager?.createNotificationChannel(channel)
        }
    }
}