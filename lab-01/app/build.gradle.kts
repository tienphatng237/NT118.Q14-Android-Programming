plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.lab01_assignment"
    compileSdk = 36  // nâng lên 36 để match yêu cầu thư viện mới

    defaultConfig {
        applicationId = "com.example.lab01_assignment"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
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
}

dependencies {
    implementation(libs.appcompat.v170)
    implementation(libs.material.v1120)
    implementation(libs.constraintlayout.v220)
}
