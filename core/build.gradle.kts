import Dependencies.DI
import Dependencies.Kotlin
import Dependencies.Network
import Dependencies.AndroidX
import Dependencies.RX
import Dependencies.View
import Dependencies.Misc
import Dependencies.Test

plugins {
    androidLibrary
    kotlin(kotlinAndroid)
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
    implementation(Kotlin.stdlib)
    implementAll(View.components)
    implementAll(DI.components)
    implementAll(Network.components)
    implementAll(RX.components)
    implementation(Misc.gson)

    kapt(DI.AnnotationProcessor.daggerHiltGoogle)
    kapt(DI.AnnotationProcessor.daggerHiltAndroidx)

    AndroidX.run {
        implementation(activity)
        implementation(coreKtx)
        implementation(preferenceKts)
        implementation(crypto)
        implementation(lifeCycleExt)
    }

    testImplementation(Test.junit)
    androidTestImplementation(Test.espresso)
    androidTestImplementation(Test.testExt)
}
