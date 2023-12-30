plugins {
    //trick: for the same plugin versions in all sub-modules
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)

    id("org.jetbrains.kotlin.jvm") version "1.8.20" apply(false)
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.5.0") apply(false)



}



buildscript {
    dependencies {
        classpath("dev.icerock.moko:resources-generator:0.23.0")
            classpath ("com.squareup.sqldelight:gradle-plugin:1.5.5")

    }

    repositories {
        google()
        mavenCentral()
    }

}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
