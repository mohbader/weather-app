plugins {
    alias(libs.plugins.system.plugin.presentation)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.weather.home.presentation"
}

dependencies{
    implementation(projects.features.home.domain)
}