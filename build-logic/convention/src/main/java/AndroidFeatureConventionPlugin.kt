import com.my.convention.coilKts
import com.my.convention.coilKtsCompose
import com.my.convention.composeNav
import com.my.convention.hiltComposeNav
import com.my.convention.kotlinCoroutines
import com.my.convention.libs
import com.my.convention.material3
import com.my.convention.ui
import com.my.convention.uiGraphics
import com.my.convention.uiTooling
import com.my.convention.uiToolingPreview
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("weatherapp.android.library")
                apply("weatherapp.android.hilt")
                apply("weatherapp.android.library.compose")
                apply("kotlinx-serialization")
            }


            dependencies {
                add("testImplementation", kotlin("test"))
                add("androidTestImplementation", kotlin("test"))

                add("implementation", libs.coilKts)
                add("implementation", libs.coilKtsCompose)
                add("implementation", libs.kotlinCoroutines)
                add("implementation", libs.composeNav)
                add("implementation", libs.hiltComposeNav)
                add("implementation", libs.material3)
                add("implementation", libs.ui)
                add("implementation", libs.uiToolingPreview)
                add("implementation", libs.uiTooling)
                add("implementation", libs.uiGraphics)
            }
        }
    }
}
