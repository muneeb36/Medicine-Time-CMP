plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.9.0"
    id("dev.icerock.mobile.multiplatform-resources")
    id("com.squareup.sqldelight")

}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    jvmToolchain(11)


    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true

        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
        extraSpecAttributes["exclude_files"] = "['src/commonMain/resources/**', 'src/iosMain/resources/MR/**']"
    }

    sourceSets {
        val sqlDelightVersion = "1.5.5"
        val koin = "3.5.0"
        val multiplatformSettings = "1.0.0"


        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                // compose
                implementation(compose.runtime)
                implementation(compose.material)
                implementation(compose.material3)
                api(compose.materialIconsExtended)
                api(compose.foundation)
                api(compose.animation)
                
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // moko
                api("dev.icerock.moko:resources:0.23.0")
                api("dev.icerock.moko:resources-compose:0.23.0")
                api("dev.icerock.moko:resources-test:0.23.0")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                implementation("com.moriatsushi.insetsx:insetsx:0.1.0-alpha10")

                //koin
                implementation("io.insert-koin:koin-core:$koin")
                implementation("io.insert-koin:koin-compose:1.0.4")

                api("com.squareup.sqldelight:runtime:$sqlDelightVersion")


                // Navigation
                api("moe.tlaster:precompose:1.5.4")
                // For ViewModel intergration
                api("moe.tlaster:precompose-viewmodel:1.5.4")

                // ShredPrefrences Like
                implementation("com.russhwolf:multiplatform-settings:$multiplatformSettings")
                implementation("com.russhwolf:multiplatform-settings-coroutines:$multiplatformSettings")

                // datetime
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")

            }
        }


        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
            }
        }

        val iosX64Main by getting {
            resources.srcDirs("build/generated/moko/iosX64Main/src")
        }
        val iosArm64Main by getting {
            resources.srcDirs("build/generated/moko/iosArm64Main/src")
        }
        val iosSimulatorArm64Main by getting {
            resources.srcDirs("build/generated/moko/iosSimulatorArm64Main/src")
        }
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
//                implementation("io.ktor:ktor-client-darwin:2.3.3")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                api("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by getting {
            dependsOn(commonTest)
            dependencies{
                api("com.squareup.sqldelight:runtime:$sqlDelightVersion")
            }
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)

        }

        val androidMain by getting {
            dependencies {
                api("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
                implementation("io.insert-koin:koin-android:$koin")

                implementation(compose.uiTooling)

                kotlin{
                    jvmToolchain(11)
                }
                java {
                    toolchain {
                        languageVersion.set(JavaLanguageVersion.of(11))
                    }
                }
            }
        }
    }
}


android {
    namespace = "com.muneeb_dev.medicinetime_cmp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }

    sourceSets {
        getByName("main").java.srcDirs("build/generated/moko/androidMain/src")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin{
        jvmToolchain(11)
    }
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(11))
        }
    }
}


multiplatformResources {
        multiplatformResourcesPackage = "com.muneeb_dev.medicinetime_cmp"
        multiplatformResourcesClassName = "SharedRes"
    }

sqldelight {
    database("MedicineDatabase"){
        packageName = "com.muneeb_dev.medicinetime_cmp.database"
        sourceFolders = listOf("sqldelight")
        deriveSchemaFromMigrations = true
        verifyMigrations = true
    }
    linkSqlite = true
}
