pluginManagement {
    repositories {
        maven { setUrl("https://kotlin.bintray.com/kotlinx") }
        mavenCentral()
        maven { setUrl("https://plugins.gradle.org/m2/") }
    }
}

rootProject.name = "android-ktx"
include(":library")
