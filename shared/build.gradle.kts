import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("app.cash.sqldelight")
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = false
        }

        iosTarget.binaries.all {
            linkerOpts("-lsqlite3")
            freeCompilerArgs += "-Xbinary=bundleId=com.cksckckcks.downloadifyoucan.Shared"
        }
    }

    sourceSets {
        commonMain.dependencies {
            // SQLDelight
            implementation("app.cash.sqldelight:runtime:2.1.0")
            implementation("app.cash.sqldelight:coroutines-extensions:2.1.0")

            // time
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")
        }

        androidMain.dependencies {
            // SQLDelight Android
            implementation("app.cash.sqldelight:android-driver:2.1.0")
        }

        iosMain.dependencies {
            // SQLDelight iOS
            implementation("app.cash.sqldelight:native-driver:2.1.0")
        }
    }
}

sqldelight {
    databases {
        create("DownloadIfYouCanDatabase") {
            packageName.set("com.cksckckcks.downloadifyoucan.database")
        }
    }
}

android {
    namespace = "com.downloadifyoucan.shared"
    compileSdk = 34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    defaultConfig {
        minSdk = 24
    }
}