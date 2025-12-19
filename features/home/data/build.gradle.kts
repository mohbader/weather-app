plugins {
    alias(libs.plugins.system.plugin.data)
}

android {
    namespace = "com.weather.home.data"
}

dependencies {
    implementation(projects.features.home.domain)
}