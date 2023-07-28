package com.example.services_and_workmanager

import android.annotation.SuppressLint
import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.Message
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MyIntentService : IntentService(NAME) {


    @SuppressLint("NotificationId0")
    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        setIntentRedelivery(false)
        notificationManager()
        startForeground(NOTIFICATION_ID, notification())
    }

    private fun notification() = NotificationCompat.Builder(this, CHANNEL_ID_2)
            .setContentTitle("Title")
            .setContentText("Text")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()


    private fun notificationManager() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificatioChannel = NotificationChannel(
                CHANNEL_ID_2,
                CHANNEL_NAME_2,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificatioChannel)
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        log("onHandleIntent")
        for (i in 0 until 25) {
            Thread.sleep(1000)
            log("Timer $i")
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }


    private fun log(message: String) {
        Log.d("MyService", "MyforegroundService: $message")
    }

    companion object {
        private const val EXTRA_START = "start"
        private const val CHANNEL_ID_2 = "channel_id"
        private const val CHANNEL_NAME_2 = "channel_name"
        private const val NOTIFICATION_ID = 1
        private const val NAME = "MyIntentService"

        fun newIntent(context: Context): Intent {
            return Intent(context, MyIntentService::class.java)
        }
    }
}
