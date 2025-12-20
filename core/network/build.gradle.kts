import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.system.plugin.library)
    alias(libs.plugins.system.plugin.hilt)
}

android {
    namespace = "com.weather.network"

    buildFeatures{
        buildConfig = true
    }
    defaultConfig {
        val weatherKey = project.findProperty("weather.api.key") ?: ""
        val weatherUrl = project.findProperty("weather.api.url") ?: ""

        buildConfigField("String", "WEATHER_API_KEY", "\"$weatherKey\"")
        buildConfigField("String", "WEATHER_BASE_URL", "\"$weatherUrl\"")
    }
}

dependencies {
    implementation(libs.http.clint)
    implementation(libs.http.log)
    implementation(libs.retrofit)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.retrofit.converter.moshi)
}