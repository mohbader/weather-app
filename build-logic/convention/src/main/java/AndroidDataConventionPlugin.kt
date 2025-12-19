import com.my.convention.libs
import com.my.convention.retrofit
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDataConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("weatherapp.android.library")
                apply("weatherapp.android.hilt")
            }

            dependencies {
                add("implementation", project(":core:network"))
                add("implementation", libs.retrofit)
            }
        }
    }

}