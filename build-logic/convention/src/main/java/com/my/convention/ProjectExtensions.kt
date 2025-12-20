/*
 * Copyright 2023 The Android Open Source Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.my.convention

import com.android.build.gradle.internal.dependency.PluginDependencies
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.gradle.api.provider.Provider
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.plugin.use.PluginDependency

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal val VersionCatalog.versionKotlinCompiler: String
    get() = findVersionOrThrow("androidxKotlinCompiler")
internal val VersionCatalog.targetSdk: String
    get() = findVersionOrThrow("targetSdk")
internal val VersionCatalog.versionCode: String
    get() = findVersionOrThrow("versionCode")
internal val VersionCatalog.versionName: String
    get() = findVersionOrThrow("versionName")
internal val VersionCatalog.minTargetSdk: String
    get() = findVersionOrThrow("minSdk")
internal val VersionCatalog.compileSdk: String
    get() = findVersionOrThrow("compileSdk")
internal val VersionCatalog.coilKts: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("coil.kt")

internal val VersionCatalog.coilKtsCompose: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("coil.kt.compose")
internal val VersionCatalog.kotlinCoroutines: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("kotlinx.coroutines.android")
internal val VersionCatalog.roomRuntime: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("room.runtime")
internal val VersionCatalog.roomKts: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("room.ktx")
internal val VersionCatalog.roomCompiler: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("room.compiler")
internal val VersionCatalog.hiltAndroid: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("hilt.android")
internal val VersionCatalog.hiltCompiler: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("hilt.compiler")
internal val VersionCatalog.composeNav: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("compose.navigation")

internal val VersionCatalog.hiltComposeNav: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("hilt.compose.navigation")

internal val VersionCatalog.composeRunTime: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("androidx.compose.runtime")

internal val VersionCatalog.material3: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("material3")

internal val VersionCatalog.ui: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("ui")

internal val VersionCatalog.uiTooling: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("ui.tooling")

internal val VersionCatalog.uiToolingPreview: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("ui.tooling.preview")

internal val VersionCatalog.uiGraphics: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("ui.graphics")

internal val VersionCatalog.composeCompiler: Provider<PluginDependency>
    get() = findPluginOrThrow("kotlin.compose")

internal val VersionCatalog.retrofit: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("retrofit")


internal val VersionCatalog.moshi: Provider<MinimalExternalModuleDependency>
    get() = findLibraryOrThrow("moshi")


private fun VersionCatalog.findLibraryOrThrow(name: String) =
    findLibrary(name)
        .orElseThrow { NoSuchElementException("Library $name not found in version catalog") }

private fun VersionCatalog.findPluginOrThrow(name: String) =
    findPlugin(name)
        .orElseThrow { NoSuchElementException("Plugin $name not found in version catalog") }
        ;

private fun VersionCatalog.findVersionOrThrow(name: String) =
    findVersion(name)
        .orElseThrow { NoSuchElementException("Version $name not found in version catalog") }
        .requiredVersion