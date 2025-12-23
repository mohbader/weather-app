plugins {
    alias(libs.plugins.system.plugin.library)
    alias(libs.plugins.system.plugin.hilt)
}

android {
    namespace = "com.weather.datastore"
}
dependencies{
    implementation(libs.datastore.preferences)
}