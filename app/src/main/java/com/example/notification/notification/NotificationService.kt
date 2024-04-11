package com.example.notification.notification

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson

class NotificationService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.isNotEmpty()) {
            try {
                Log.d("landing_type", "data received")
                val map: Map<String, String> = remoteMessage.data
                val gson = Gson()
                val item: NotificationModel? = gson.fromJson(map["data"], NotificationModel::class.java)
                item?.let {
                    GenericNotificationManager.handleGenericNotification(applicationContext, it)
                }
            } catch (e: Exception) {
                Log.d("notification_exception", e.toString())
            }
        }
    }
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}
