import com.android.build.api.dsl.ApplicationExtension
import com.my.convention.configureKotlinAndroid
import com.my.convention.libs
import com.my.convention.targetSdk
import com.my.convention.versionCode
import com.my.convention.versionName
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)


                defaultConfig.apply {
                    targetSdk = libs.targetSdk.toInt()
                    compileSdk = libs.targetSdk.toInt()
                    versionCode = libs.versionCode.toInt()
                    versionName = libs.versionName
                    applicationId = "com.weather.app"
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables.apply {
                        useSupportLibrary = true
                    }
                }

                packaging.apply {
                    resources.apply {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
            }

        }
    }

}