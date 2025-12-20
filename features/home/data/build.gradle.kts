plugins {
    alias(libs.plugins.system.plugin.data)
    alias(libs.plugins.ktor.serialization)
}

android {
    namespace = "com.weather.home.data"
}

dependencies {
    implementation(projects.features.home.domain)
}