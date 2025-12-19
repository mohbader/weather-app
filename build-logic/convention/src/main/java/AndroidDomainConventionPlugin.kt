import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidDomainConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("weatherapp.android.library")
            }
        }
    }
}