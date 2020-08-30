import Dependencies.DI
import Dependencies.Kotlin
import Dependencies.RX
import Dependencies.Network
import Dependencies.Test
import Dependencies.Misc

plugins {
    androidLibrary
    kotlin(kotlinAndroid)
    kotlin(kotlinAndroidExtension)
    kotlin(kotlinKapt)
    daggerHilt
}

android {
    compileSdkVersion(Config.Version.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Config.Version.minSdkVersion)
        targetSdkVersion(Config.Version.targetSdkVersion)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildTypes {
        named(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
        }
    }
}

dependencies {
    implementation(project(ProjectModules.domain))
    implementation(project(ProjectModules.core))
    implementAll(Kotlin.components)
    implementAll(RX.components)
    implementAll(Network.components)
    implementation(Misc.timber)
    implementAll(DI.components)

    kapt(DI.AnnotationProcessor.daggerHiltGoogle)
    kapt(DI.AnnotationProcessor.daggerHiltAndroidx)
    kapt(Network.AnnotationProcessor.moshi)

    testImplementation(Test.junit)
    testImplementation(Test.assertJ)
    testImplementation(Test.mokitoKotlin)
}
