plugins {
    kotlin("jvm")
    alias(libs.plugins.ksp)
}

dependencies {
    ksp(libs.moshi.codegen)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.adapters)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
}
