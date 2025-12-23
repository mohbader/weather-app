pluginManagement {
    includeBuild("build-logic") // Include the build-logic
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "WeatherApp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":core:theme")
include(":core:network")
include(":features:home:presentation")
include(":features:home:data")
include(":features:home:domain")
include(":core:datastore")
include(":features:search:presentation")
include(":features:search:domain")
include(":features:search:data")
include(":core:navigation")
