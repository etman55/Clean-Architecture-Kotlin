import Dependencies.AndroidX
import Dependencies.DI
import Dependencies.Kotlin
import Dependencies.RX
import Dependencies.Network
import Dependencies.View
import Dependencies.Misc
import Dependencies.FireBase
import Dependencies.Test

plugins {
    androidApplication
    kotlin(kotlinAndroid)
    kotlin(kotlinAndroidExtension)
    kotlin(kotlinKapt)
    id("kotlin-android")
    safeArgs
    daggerHilt
}

android {
    compileSdkVersion(Config.Version.targetSdkVersion)

    defaultConfig {
        applicationId = Config.Android.applicationId
        minSdkVersion(Config.Version.minSdkVersion)
        compileSdkVersion(Config.Version.compileSdkVersion)
        targetSdkVersion(Config.Version.targetSdkVersion)
        versionCode = Config.Version.versionCode
        versionName = Config.Version.versionName
        testInstrumentationRunner = Config.Android.testInstrumentationRunner
    }
    androidExtensions {
        isExperimental = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    setDefaultSigningConfigs(project)

    buildTypes {
        named(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(ProjectModules.core))
    implementation(project(ProjectModules.data))
    implementation(project(ProjectModules.domain))
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.1")
    implementAll(View.components)
    implementation(Kotlin.stdlib)
    implementation(Network.moshi)
    implementation(Misc.timber)
    implementAll(RX.components)
    implementAll(DI.components)
    implementAll(FireBase.components)

    AndroidX.run {
        implementation(activity)
        implementation(coreKtx)
        implementation(navigationFragmentKtx)
        implementation(navigationUiKtx)
        implementation(lifeCycleExt)
        implementation(viewModel)
        implementation(lifecycleReactiveStreams)
    }

    kapt(DI.AnnotationProcessor.daggerHiltGoogle)
    kapt(DI.AnnotationProcessor.daggerHiltAndroidx)

    testImplementation(Test.junit)
    androidTestImplementation(Test.espresso)
    androidTestImplementation(Test.testExt)

}
