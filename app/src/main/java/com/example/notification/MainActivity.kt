package com.example.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notification.notification.FcmAsyncTask
import com.google.firebase.crashlytics.FirebaseCrashlytics
import java.util.Random

private const val DEFAULT_CHANNEL_ID = "default_channel"
class MainActivity : AppCompatActivity() {
    private var notificationId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                FcmAsyncTask(applicationContext).execute()
            }
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(Throwable("FCM Failed to register $e"))
        }
        val sendNotification = findViewById<Button>(R.id.sendNotification)
        sendNotification.setOnClickListener {
//        createNotification()
          createCustomNotification()
        }
    }

    private fun createNotification() {
            val intent = Intent(this, HomeActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

            val notificationId = Random().nextInt(60000)
            val builder = NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My Notification")
                .setContentText("This is a default notification.")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            val notificationManager = NotificationManagerCompat.from(this)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    DEFAULT_CHANNEL_ID,
                    "Channel Name",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.VIBRATE), 12)
            } else {
                notificationManager.notify(notificationId, builder.build())
            }
    }
    private fun createCustomNotification(){
        val customLayout = RemoteViews(packageName, R.layout.custom_notification_layout)
        customLayout.setTextViewText(R.id.notification_title, "Custom Title")
        customLayout.setTextViewText(R.id.notification_message, "Custom Message")
        val builder = NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle()) // Set custom style
            .setCustomContentView(customLayout) // Set custom layout
        val notificationManager = NotificationManagerCompat.from(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                DEFAULT_CHANNEL_ID,
                "Channel Name",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.VIBRATE), 12)
        } else {
            notificationManager.notify(notificationId, builder.build())
        }
    }
}