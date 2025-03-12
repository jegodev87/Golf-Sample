plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.sample.golf"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sample.golf"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        val releaseApiKey: String? = findProperty("API_KEY") as String?

        // Check if the API key is available, otherwise throw an exception
        if (releaseApiKey.isNullOrEmpty()) {
            throw GradleException("API key not found in local.properties")
        }

        buildConfigField("String", "GOLF_API_KEY", "\"$releaseApiKey\"")
    }

    buildTypes {

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://api.golfcourseapi.com/\"")
        }
        debug {

            buildConfigField("String", "BASE_URL", "\"https://api.golfcourseapi.com/\"")
        }
      /*  create("qa"){
            buildConfigField("String", "BASE_URL", "\"https://api.golfcourseapi.com/\"")
        }
        create("dev"){
            buildConfigField("String", "BASE_URL", "\"https://api.golfcourseapi.com/\"")
        }
        create("uat"){
            buildConfigField("String", "BASE_URL", "\"https://api.golfcourseapi.com/\"")
        }*/
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation (libs.androidx.room.runtime)
    ksp (libs.androidx.room.compiler)
    implementation(libs.androidx.navigation.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.hilt.android)
    ksp(libs.dagger.compiler)
    ksp(libs.hilt.compiler)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.logging.interceptor)
    implementation (libs.androidx.lifecycle.viewmodel.ktx )
    implementation (libs.androidx.activity.ktx )
    implementation (libs.androidx.core.splashscreen)
    implementation (libs.androidx.recyclerview)
    //Dimen
    implementation (libs.ssp.android)
    implementation (libs.sdp.android)
    implementation (libs.shimmer)


}