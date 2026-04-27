import com.my.convention.kotlinxCoroutinesTest
import com.my.convention.libs
import com.my.convention.mockk
import com.my.convention.turbine
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
                add("implementation", project(":core:navigation"))
                add("implementation", project(":core:datastore"))

                add("testImplementation", libs.mockk)
                add("testImplementation", libs.kotlinxCoroutinesTest)
                add("testImplementation", libs.turbine)

            }
        }
    }
}