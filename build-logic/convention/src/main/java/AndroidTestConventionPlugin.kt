import com.android.build.gradle.TestExtension
import com.my.convention.configureKotlinAndroid
import com.my.convention.libs
import com.my.convention.targetSdk
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.test")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<TestExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libs.targetSdk.toInt()
            }
        }
    }

}
