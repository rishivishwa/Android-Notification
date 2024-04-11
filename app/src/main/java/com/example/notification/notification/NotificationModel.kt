package com.example.notification.notification

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NotificationModel : Serializable {
    @SerializedName("title")
    val title: String? = null
    @SerializedName("body")
    val body: String? = null
    @SerializedName("large_icon")
    val largeIcon: String? = null
    @SerializedName("big_image")
    val bigImage: String? = null
    @SerializedName("landing_type")
    val landingType: String? = null
    @SerializedName("landing_value")
    val landingValue: String? = null
    @SerializedName("app_version")
    val appVersion: String? = null
    @SerializedName("gradient_colors")
    val gradientColors: String? = null
}
