plugins {
    id("org.jetbrains.compose") version "0.4.0"
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

group = "com.ger"
version = "1.0"

repositories {
    google()
}

dependencies {
    implementation(project(":common"))
    implementation(project(":tcpClient"))
    implementation("androidx.activity:activity-compose:1.3.0-beta02")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.ger.android"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
