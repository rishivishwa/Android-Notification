package com.example.notification.notification;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NotificationDataModel implements Serializable {

    @SerializedName("title")
    public  String title="";

    @SerializedName("body")
    public  String body="";

    @SerializedName("big_image")
    public  String big_image="";

    @SerializedName("landing_type")
    public String landing_type="";

    @SerializedName("landing_value")
    public String landing_value;

    @SerializedName("app_version")
    public  String app_version="";

    @SerializedName("toolbar_title")
    public  String toolbar_title="";

    @SerializedName("large_icon")
    public String large_icon="";


    public String getToolbar_title() {
        return toolbar_title;
    }

    public void setToolbar_title(String toolbar_title) {
        this.toolbar_title = toolbar_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBig_image() {
        return big_image;
    }

    public void setBig_image(String big_image) {
        this.big_image = big_image;
    }

    public String getLarge_icon() {
        return large_icon;
    }


    public void setLarge_icon(String large_icon) {
        this.large_icon = large_icon;
    }

    public String getLanding_type() {
        return landing_type;
    }

    public void setLanding_type(String landing_type) {
        this.landing_type = landing_type;
    }

    public String getLanding_value() {
        return landing_value;
    }

    public void setLanding_value(String landing_value) {
        this.landing_value = landing_value;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    @Override
    public String toString() {
        return "NotificationModel{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", big_image='" + big_image + '\'' +
                ", landing_type='" + landing_type + '\'' +
                ", landing_value='" + landing_value + '\'' +
                ", app_version='" + app_version + '\'' +
                '}';
    }
}
