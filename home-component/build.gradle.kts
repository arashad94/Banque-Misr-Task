plugins {
    id("com.android.library")
    id("kotlin-android")
    alias(libs.plugins.ksp)
    id("dagger.hilt.android.plugin")
}

apply(from = "../coverage/coverageReport.gradle")

android {
    namespace = "com.banquemisr.home_component"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf(
            "-Xinline-classes",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=kotlinx.coroutines.FlowPreview"
        )
    }
}

dependencies {
    implementation(project(":retrofitfactory"))
    implementation(project(":network-utils"))
    implementation(project(":shared"))
    implementation(project(":bmcache"))

    implementation(libs.kotlin.coroutines.core)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    ksp(libs.moshi.codegen)
    implementation(libs.moshi)
    implementation(libs.moshi.adapters)
    implementation(libs.retrofit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
}
