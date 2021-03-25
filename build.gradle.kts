plugins {
    id("io.codearte.nexus-staging") version "0.30.0"
//    id("com.github.ben-manes.versions") version "0.38.0"
}

buildscript {
    val kotlin_version: String by extra
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        maven("https://kotlin.bintray.com/kotlinx")
        maven("https://maven-central-eu.storage-download.googleapis.com/repos/central/data")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.7.0.0")
    }
}


nexusStaging {
    packageGroup = "app.moviebase"
    stagingProfileId = findProperty("SONATYPE_STAGING_PROFILE_ID") as String?
    username = findProperty("SONATYPE_USER") as String?
    password = findProperty("SONATYPE_PASSWORD") as String?
}

allprojects {
    repositories {
        maven("${System.getenv("HOME")}/.m2/repository")
        maven("https://maven-central-eu.storage-download.googleapis.com/repos/central/data")
        maven("https://jitpack.io")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}


