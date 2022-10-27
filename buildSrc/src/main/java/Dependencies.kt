object Versions {
    val versionMajor = 1
    val versionMinor = 4
    val versionPatch = 2

    val versionCode = versionMajor * 1000 + versionMinor * 100 + versionPatch * 10
    val versionName = "$versionMajor.$versionMinor.$versionPatch"

    val minSdk = 21
    val targetSdk = 31
    val compileSdk = 31
    val buildTools = "31.0.0"

    // Plugins
    val androidGradle = "7.0.4"
    val androidJunit = "1.7.1.1"
    val dokka = "1.4.30"
    val nexus = "1.0.0"

    // Kotlin
    val kotlin = "1.6.21"
    val coroutines = "1.6.1"

    // AndroidX
    val core = "1.7.0"
    val constraintLayout = "2.1.3"
    val preference = "1.2.0"
    val navigation = "2.4.2"
    val recyclerView = "1.2.1"
    val viewPager2 = "1.1.0-alpha01"
    val work = "2.7.1"
    val collection = "1.1.0"

    // Google
    val material = "1.6.0"

    // Testing
    val junitJupiter = "5.8.2"
    val truth = "1.1.3"
    val mockitoInline = "4.5.1"
}

object Plugins {
    val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    val androidJunit = "de.mannodermaus.gradle.plugins:android-junit5:${Versions.androidJunit}"
    val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}"
}

object Libs {

    object Kotlin {
        val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }

    object AndroidX {
        val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        val preferenceKtx = "androidx.preference:preference-ktx:${Versions.preference}"
        val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        val coreKtx = "androidx.core:core-ktx:${Versions.core}"
        val navigationUi = "androidx.navigation:navigation-ui:${Versions.navigation}"
        val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
        val collection = "androidx.collection:collection:${Versions.collection}"
        val work = "androidx.work:work-runtime-ktx:${Versions.work}"
    }

    object Google {
        val material = "com.google.android.material:material:${Versions.material}"
    }

    object Testing {
        val truth = "com.google.truth:truth:${Versions.truth}"
        val jupiter = "org.junit.jupiter:junit-jupiter-api:${Versions.junitJupiter}"
        val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junitJupiter}"
        val mockito = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    }
}
