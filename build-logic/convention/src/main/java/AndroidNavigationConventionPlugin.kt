
import com.my.convention.composeRunTime
import com.my.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidNavigationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("baseapp.android.library")
                apply("baseapp.android.library.compose")
            }

            dependencies {
                add("implementation", libs.composeRunTime)
            }
        }
    }
}