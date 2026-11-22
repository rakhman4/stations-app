buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        // Match AGP's built-in Kotlin compiler to the Compose and serialization plugins.
        classpath(libs.kotlin.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
}