
buildscript {
    repositories {
        // Your repositories
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.google.firebase:firebase-crashlytics-gradle:2.9.1")
    }
}
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.0" apply false
    id("com.google.gms.google-services") version "4.3.13" apply false
}