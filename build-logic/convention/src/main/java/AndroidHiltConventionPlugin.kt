
import com.my.convention.hiltAndroid
import com.my.convention.hiltCompiler
import com.my.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
                apply("com.google.devtools.ksp")
            }

            dependencies {
                "implementation"(libs.hiltAndroid)
                "ksp"(libs.hiltCompiler)
            }

        }
    }

}