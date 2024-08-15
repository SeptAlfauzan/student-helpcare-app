import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.googleGmsGoogleServices)
}

fun loadFlavorProperties(flavor: String): Properties {
    val propertiesFile = project.rootProject.file("$flavor.properties")
    val properties = Properties()
    if (propertiesFile.exists()) {
        properties.load(propertiesFile.inputStream())
    }
    return properties
}

android {
    namespace = "com.kudos.studenthelpcare"
    compileSdk = 34

    flavorDimensions += "environment"
    productFlavors {
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            val properties = loadFlavorProperties("dev")

            buildConfigField(
                type = "String", name = "MQTT_PASSWORD", value = properties.getProperty("MQTT_PASSWORD") ?: ""
            )
        }
        create("prod") {
            dimension = "environment"
            val properties = loadFlavorProperties("prod")

            buildConfigField(
                    type = "String", name = "MQTT_PASSWORD", value = properties.getProperty("MQTT_PASSWORD") ?: ""
            )
        }
    }



    defaultConfig {

        applicationId = "com.kudos.studenthelpcare"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        debug {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("io.coil-kt:coil-compose:2.6.0")


    //Dagger hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")


    implementation("com.google.code.gson:gson:2.11.0")

    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    //extended icons
    implementation("androidx.compose.material:material-icons-extended:1.6.2")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:4.4.1")


    //Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation("com.halilibo.compose-richtext:richtext-ui-material3:1.0.0-alpha01")
    implementation("com.halilibo.compose-richtext:richtext-markdown:1.0.0-alpha01")
    implementation("com.halilibo.compose-richtext:richtext-commonmark:1.0.0-alpha01")

    //pull refresh
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    //splash screen
    implementation("androidx.core:core-splashscreen:1.0.0")
    //pdf reader
    implementation("io.github.grizzi91:bouquet:1.1.2")
}