plugins {
    alias(libs.plugins.system.plugin.presentation)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.weather.presentation"
}

dependencies{
    implementation(projects.features.search.domain)
}