plugins {
    alias(libs.plugins.system.plugin.presentation)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.core.worker"
}

dependencies {
    implementation(libs.wrok.manger)
    implementation(projects.features.home.domain)
}