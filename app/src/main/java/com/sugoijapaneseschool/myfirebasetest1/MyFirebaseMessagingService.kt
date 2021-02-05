package com.sugoijapaneseschool.myfirebasetest1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    val TAG = "Service"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification != null) {
            // Firebase FCM 에서 받은건 Log도 찍고
            Log.d(TAG, "From: " + remoteMessage.from)
            Log.d(TAG, "Notification Message Body: " + remoteMessage.notification!!.body)

            // 팝업도 띄워보자
            showNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification(title: String?, body: String?) {

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT)

        val channel = NotificationChannel("MyCHANNEL_ID", "MyName", NotificationManager.IMPORTANCE_DEFAULT).apply {
            description = "descriptionText"
        }

        // 채널등록
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)


        val builder = NotificationCompat.Builder(this, "MyCHANNEL_ID")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(body)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notificationManager.notify(0, builder.build())
    }
}