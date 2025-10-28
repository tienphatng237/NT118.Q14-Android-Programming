plugins {
    alias(libs.plugins.android.application)
    id("jacoco")
}

android {
    namespace = "com.example.lab_03"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.lab_03"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isTestCoverageEnabled = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.gridlayout:gridlayout:1.0.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn("testDebugUnitTest")
    reports {
        xml.required.set(true)
        html.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco"))
    }
    val debugTree = fileTree(
        mapOf("dir" to "$buildDir/tmp/kotlin-classes/debug", "includes" to listOf("**/*.class"))
    )
    val mainSrc = "$projectDir/src/main/java"
    sourceDirectories.setFrom(files(mainSrc))
    classDirectories.setFrom(files(debugTree))
    executionData.setFrom(
        fileTree(mapOf("dir" to buildDir, "includes" to listOf("jacoco/testDebugUnitTest.exec")))
    )
}
