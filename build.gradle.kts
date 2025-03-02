// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
}

buildscript {
    repositories {
        mavenCentral()
        google()
        maven("https://jitpack.io")
    }
    dependencies {
        classpath(libs.android.gradle)
        classpath(libs.hilt.gradle.plugin)
        classpath(libs.kover.plugin)
        classpath(libs.kotlin.gradle)
    }
}

apply(from = "coverage/completeCoverageReport.gradle")
