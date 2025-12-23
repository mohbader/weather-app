import com.my.convention.diInject
import com.my.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDomainConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("weatherapp.android.library")
                apply("weatherapp.android.hilt")
            }

            dependencies{
                add("implementation", libs.diInject)
            }
        }
    }
}