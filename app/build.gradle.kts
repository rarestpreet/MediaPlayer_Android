import java.util.regex.Pattern.compile

//import org.jetbrains.kotlin.gradle.internal.kapt.incremental.UnknownSnapshot.classpath

plugins {
    id("com.android.application") version "8.6.1"
    id("org.jetbrains.kotlin.android") version "1.9.10"
    id("com.google.devtools.ksp") version "1.9.0-1.0.13"
    id("com.google.gms.google-services") // Firebase plugin
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3" // Set to the Compose compiler version
    }

    // Enable view binding
    buildFeatures {
        viewBinding = true
        compose = true // Enable Jetpack Compose
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Core AndroidX dependencies
    implementation(libs.androidx.core.ktx.v1150)
    implementation(libs.androidx.appcompat.v170)
    implementation(libs.material.v1120)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.constraintlayout)

    // Firebase dependencies
    implementation (platform(libs.firebase.bom.v3360)) // or latest version
    implementation (libs.firebase.analytics)
    implementation (libs.firebase.database)
    implementation(libs.firebase.inappmessaging.display.ktx)

    // CircleImageView
    implementation(libs.circleimageview)

    // Retrofit and OkHttp for API calls
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // JSON Parsing
    implementation(libs.gson)

    // Coroutines
    implementation(libs.coroutines.android)

    // Room Database
    implementation(libs.room.runtime)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.ui.graphics.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.animation.core.android)
    implementation(libs.material)
    ksp(libs.room.compiler)
}
apply(plugin = "com.google.gms.google-services")