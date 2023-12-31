package com.example.services_and_workmanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.services_and_workmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d("MyService", "Start")
        binding.simpleService.setOnClickListener{
            Log.d("MyService", "Start")
            stopService(MyforegroundService.newIntent(this))
            startService(MyService.newIntent(this, 25))
        }
        binding.foregroundService.setOnClickListener{
            ContextCompat.startForegroundService(
                this,
                MyforegroundService.newIntent(this)
            )
        }
    }

//    private fun showNotification(){
//        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val notificatioChannel =  NotificationChannel(
//                CHANNEL_ID,
//                CHANNEL_NAME,
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            notificationManager.createNotificationChannel(notificatioChannel)
//        }
//
//        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setContentTitle("Title")
//            .setContentText("Text")
//            .setSmallIcon(R.drawable.ic_launcher_background)
//            .build()
//
//        notificationManager.notify(1, notification)
//    }
//
//    companion object{
//        private const val CHANNEL_ID = "channel_id"
//        private const val CHANNEL_NAME = "channel_name"
//
//    }
}