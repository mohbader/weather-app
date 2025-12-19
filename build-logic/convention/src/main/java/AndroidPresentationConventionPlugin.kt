import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidPresentationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("weatherapp.android.library")
                apply("weatherapp.android.hilt")
                apply("weatherapp.android.library.compose")
                apply("weatherapp.android.feature")
            }


            dependencies {
                add("implementation", project(":core:theme"))
            }
        }
    }
}