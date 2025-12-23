plugins {
    alias(libs.plugins.system.plugin.data)
}

android {
    namespace = "com.weather.search.data"
}

dependencies{
    implementation(projects.features.search.domain)
}