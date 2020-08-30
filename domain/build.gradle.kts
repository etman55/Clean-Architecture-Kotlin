import Dependencies.DI
import Dependencies.RX
import Dependencies.Misc
import Dependencies.Test

plugins {
    kotlin
}

dependencies {
    implementAll(RX.components)
    implementation(DI.javaxInject)
    implementation(Misc.timber)
    testImplementation(Test.junit)
}
