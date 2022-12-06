//package com.example.team_16
//
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.app.PendingIntent.FLAG_IMMUTABLE
//import android.app.PendingIntent.writePendingIntentOrNullToParcel
//import android.app.Service
//import android.content.Context
//import android.content.Intent
//import android.os.Build
//import android.os.IBinder
//import androidx.annotation.RequiresApi
//import androidx.core.app.NotificationCompat
//
//private var notificationManager: NotificationManager? = null
//const val PROGRESS_MAX = 100
//class AlarmService : Service() {
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        val contentIntent = Intent(this, StopwatchFragment::class.java)
//        val pendingIntent = PendingIntent.getActivity(this,
//        0,
//        contentIntent,
//            PendingIntent.FLAG_IMMUTABLE
//        )
//
//        val notificationBuilder = NotificationCompat.Builder(this, App.ALARM_CHANNEL_ID)
//            .setContentTitle("Service Test")
//            .setContentText("Service is in progress")
//            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
//            .setContentIntent(pendingIntent)
//            .setProgress(PROGRESS_MAX,0,false)
//    }
//
//    override fun createNotificationChannel(channelId: String, name: String, channelDescription: String) {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val importance = NotificationManager.IMPORTANCE_DEFAULT // set importance
//            val channel = NotificationChannel(channelId, name, importance).apply {
//                description = channelDescription
//            }
//            // Register the channel with the system
//            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager?.createNotificationChannel(channel)
//        }
//    }
//
//    override fun onBind(intent: Intent?): IBinder? {
//        return null
//    }
//}