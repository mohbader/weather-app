plugins {
    alias(libs.plugins.system.plugin.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.system.plugin.hilt)
    alias(libs.plugins.system.plugin.flavors)
}

android {
    buildFeatures{
        buildConfig = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled=false
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(libs.hilt.compose.navigation)

    //core
    implementation(projects.core.navigation)
    implementation(projects.core.network)
    implementation(projects.core.theme)
    implementation(projects.core.datastore)

    //features
    implementation(projects.features.home.presentation)
    implementation(projects.features.home.domain)
    implementation(projects.features.home.data)

    implementation(projects.features.search.presentation)
    implementation(projects.features.search.domain)
    implementation(projects.features.search.data)




}