package com.my.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor


enum class FlavorDimension {
    contentType
}

enum class AppFlavor(
    val dimension: FlavorDimension,
    val applicationIdSuffix: String? = null,
    val versionNameSuffix: String? = null
) {

    DEVLOPMENT(
        FlavorDimension.contentType,
        applicationIdSuffix = ".dev",
        versionNameSuffix = "-dev"
    ),

    UAT(
        FlavorDimension.contentType,
        applicationIdSuffix = ".uat",
        versionNameSuffix = "-uat"
    ),

    PRODUCTION(
        FlavorDimension.contentType
    )

}

fun configFlavor(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: AppFlavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.contentType.name
        productFlavors {
            AppFlavor.values().forEach { flavor ->
                create(flavor.name.lowercase()) {
                    dimension = flavor.dimension.name
                    flavorConfigurationBlock(this, flavor)
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (flavor.applicationIdSuffix != null && flavor.versionNameSuffix != null) {
                            applicationIdSuffix = flavor.applicationIdSuffix
                            versionNameSuffix = flavor.versionNameSuffix
                        }
                    }
                }
            }
        }
    }
}