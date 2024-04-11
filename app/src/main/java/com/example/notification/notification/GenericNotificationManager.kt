package com.example.notification.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.util.Log
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.notification.HomeActivity
import com.example.notification.MainActivity
import com.example.notification.R
import java.util.Random

object GenericNotificationManager {
    private var LANDING_TYPE_HOME = "HOME"
    private var notificationManager: NotificationManager? = null
    private const val ADMIN_CHANNEL_ID = "converter_channel"
    private const val ADMIN_CHANNEL_NAME = "Notification_Channel"
    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(ADMIN_CHANNEL_ID, ADMIN_CHANNEL_NAME, importance)
            channel.description = "Notification for reminding the app"
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun handleGenericNotification(context: Context, notification: NotificationModel?) {
        createNotificationChannel(context)
        if (notification != null) {
            var intent: Intent? = null
            if (LANDING_TYPE_HOME == notification.landingType) {
                intent = getIntentForHome(context, notification)
            }
            if (intent == null) {
                intent = getDefaultIntent(context)
            }
            val requestCode = Random().nextInt(1000)
            val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
            } else {
                PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            }
            createNotification(context, notification, pendingIntent)
        }
    }

    private fun getIntentForHome(context: Context?, notification: NotificationModel?): Intent? {
        if (notification != null && context != null) {
            val notificationIntent = Intent(context, MainActivity::class.java)
            notificationIntent.putExtra("Name_Data","Rishjhbhf")
//            val notificationIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))

            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            return notificationIntent
        }
        return null
    }

    private fun getDefaultIntent(context: Context): Intent {
        return Intent(context, HomeActivity::class.java)
    }

    private fun createNotification(context: Context, notification: NotificationModel, pendingIntent: PendingIntent) {
        val notificationId = Random().nextInt(60000)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val title = notification.title
        val body = notification.body
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(body)) return
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder =
            NotificationCompat.Builder(context, ADMIN_CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources,R.drawable.ic_launcher_background))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSound(defaultSoundUri)
        if (!TextUtils.isEmpty(notification.bigImage)) {
            Glide.with(context)
                .load(notification.bigImage)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        val bitmap = (resource as BitmapDrawable).bitmap
                        notificationBuilder.setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                        notificationManager!!.notify(notificationId, notificationBuilder.build())
                        return false
                    }
                }).submit()
        }
        if (!TextUtils.isEmpty(notification.bigImage)) {
            if (notification.largeIcon != null) {
                Glide.with(context)
                    .load(notification.largeIcon)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                            Log.d("Notification_Issue", e.toString())
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable,
                            model: Any,
                            target: Target<Drawable>,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            val bitmap = (resource as BitmapDrawable).bitmap
                            notificationBuilder.setLargeIcon(bitmap)
                            notificationManager!!.notify(notificationId, notificationBuilder.build())
                            return false
                        }
                    }).submit()
            }
        }
        notificationManager?.notify(notificationId, notificationBuilder.build())
    }

}
