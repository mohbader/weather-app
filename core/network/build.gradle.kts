import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.system.plugin.library)
    alias(libs.plugins.system.plugin.hilt)
}

android {
    namespace = "com.weather.network"
}

dependencies {
    implementation(libs.http.clint)
    implementation(libs.http.log)
    implementation(libs.retrofit)
}