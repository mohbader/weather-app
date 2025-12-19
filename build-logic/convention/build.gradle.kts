plugins {
    `kotlin-dsl`
}

group = "com.weatherapp.buildlogic"

java {

    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "weatherapp.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "weatherapp.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "weatherapp.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "weatherapp.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "weatherapp.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidHilt") {
            id = "weatherapp.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("appFlavors") {
            id = "weatherapp.app.flavors"
            implementationClass = "AndroidApplicationFlavorsConventionPlugin"
        }

        register("androidData") {
            id = "weatherapp.android.data"
            implementationClass = "AndroidDataConventionPlugin"
        }

        register("androidDomain") {
            id = "weatherapp.android.domain"
            implementationClass = "AndroidDomainConventionPlugin"
        }

        register("androidPresentation") {
            id = "weatherapp.android.presentation"
            implementationClass = "AndroidPresentationConventionPlugin"
        }

        register("androidNavigationPlugin") {
            id = "weatherapp.android.navigation"
            implementationClass = "AndroidNavigationConventionPlugin"
        }
    }
}