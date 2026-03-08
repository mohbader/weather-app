plugins {
    alias(libs.plugins.system.plugin.library)
    alias(libs.plugins.system.plugin.hilt)
}

android {
    namespace = "com.core.worker"
}

dependencies {
    implementation(libs.wrok.manger)
    implementation(projects.features.home.domain)
}