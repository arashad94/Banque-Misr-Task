import java.util.Properties

plugins {
    id ("dagger.hilt.android.plugin")
    id("com.android.application")
    id("kotlin-android")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}

val props = Properties()

android {
    signingConfigs {
        create("releasePlaystore") {
            storeFile = rootProject.file("secrets/release_playstore")
            storePassword = props.getProperty("STORE_PASSWORD")
            keyAlias = props.getProperty("KEY_ALIAS")
            keyPassword = props.getProperty("KEY_PASSWORD")
        }
    }
    namespace = "com.banquemisr.challenge05"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.banquemisr.challenge05"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        create("staging") {
            isDebuggable = true
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".staging"
            matchingFallbacks.add("debug")

            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        }
        create("production") {
            signingConfig = signingConfigs.getByName("releasePlaystore")
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            matchingFallbacks.add("release")
        }
    }

    androidComponents {
        beforeVariants { variantBuilder ->
            if (variantBuilder.name == "release" || variantBuilder.name == "debug") {
                variantBuilder.enable = false
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
        freeCompilerArgs = listOf(
            "-Xinline-classes",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=kotlinx.coroutines.FlowPreview"
        )
    }
}

dependencies {

    implementation(project(":retrofitfactory"))
    implementation(project(":designsystem"))
    implementation(project(":shared"))
    implementation(project(":home-component"))
    implementation(project(":home-ui"))
    implementation(project(":pdp-ui"))
    implementation(project(":pdp-component"))

    ksp(libs.hilt.android.compiler)
    ksp(libs.moshi.codegen)
    implementation(libs.androidx.core)
    implementation(libs.compose.navigation)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.hilt.android)
    implementation(libs.hilt.android.navigation)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.adapters)
    implementation(libs.retrofit)
    implementation(libs.konsist)
    implementation(libs.retrofit.logging.interceptor)
    implementation(libs.retrofit.converter.moshi)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.junit)
    "stagingImplementation"(libs.chucker)
}
