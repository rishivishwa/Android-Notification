package com.example.notification.notification

import android.content.Context
import android.text.TextUtils
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class NotificationRemoteConfig {

    companion object{
        private const val TOPIC_CONFIG_KEY = "topic"

        fun getTopicValue(appCompatActivity: Context): String? {
            return try {
                FirebaseApp.initializeApp(appCompatActivity.applicationContext)
                val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
                val enable_value =
                    mFirebaseRemoteConfig.getString(TOPIC_CONFIG_KEY)
                if (TextUtils.isEmpty(enable_value)) {
                    ""
                } else enable_value
            } catch (e: java.lang.Exception) {
                ""
            }
        }
    }
}