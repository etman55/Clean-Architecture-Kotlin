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
    implementAll(View.components)
    implementAll(Kotlin.components)

    AndroidX.run {
        implementation(activity)
        implementation(coreKtx)
        implementation(navigationFragmentKtx)
        implementation(navigationUiKtx)
        implementation(lifeCycleExt)
        implementation(viewModel)
        implementation(lifecycleReactiveStreams)
    }

    testImplementation(Test.junit)
    androidTestImplementation(Test.espresso)
    androidTestImplementation(Test.testExt)

}
