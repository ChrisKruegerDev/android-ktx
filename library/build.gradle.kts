plugins {
    id("com.android.library")
    kotlin("android")
    id("de.mannodermaus.android-junit5")
    id("maven-publish")
}

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}

val version_major: String by project
val version_minor: String by project
val version_patch: String by project
val kotlin_version: String by project

group = "com.moviebase"
version = "$version_major.$version_minor.$version_patch"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.preference:preference-ktx:1.1.1")
    implementation("androidx.work:work-runtime-ktx:2.5.0-rc01")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.2")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("com.google.android.material:material:1.3.0-rc01")
    testImplementation("com.google.truth:truth:1.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testImplementation("org.mockito:mockito-inline:3.7.0")
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.2"
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = version_major.toInt() * 1000 + version_minor.toInt() * 100 + version_patch.toInt() * 10
        versionName = "$version_major.$version_minor.$version_patch"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    dexOptions {
        preDexLibraries = true
        javaMaxHeapSize = "12g"
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
        animationsDisabled = true
        unitTests.isIncludeAndroidResources = true
    }
    lintOptions {
        isIgnoreTestSources = true
        isWarningsAsErrors = true
        isCheckDependencies = true
        isAbortOnError = false
    }
}

val sourcesJar by tasks.registering(Jar::class) {
//    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

artifacts {
    add("archives", sourcesJar)
}

publishing {
    //    https://github.com/gradle/gradle/issues/11412#issuecomment-555413327
    System.setProperty("org.gradle.internal.publish.checksums.insecure", "true")

    repositories {
        maven {
            name = "bintray"
            setUrl("https://api.bintray.com/maven/moviebase/maven/android-ktx/;publish=1;override=1")

            credentials {
                username = findProperty("BINTRAY_USER") as String?
                password = findProperty("BINTRAY_API_KEY") as String?
            }
        }
    }

    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "com.moviebase"
            artifactId = "android-ktx"
            version = "$version_major.$version_minor.$version_patch"
            artifact(sourcesJar)
            artifact("$buildDir/outputs/aar/library-release.aar")

            pom {
                name.set("Android Kotlin Extensions")
                description.set("Kotlin extensions for Android.")
                url.set("https://github.com/MoviebaseApp/${project.name}")
                inceptionYear.set("2020")
                packaging = "aar"

                developers {
                    developer {
                        id.set("chrisnkrueger")
                        name.set("Christian Krüger")
                        email.set("christian.krueger@moviebase.app")
                    }
                }
                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                issueManagement {
                    system.set("GitHub Issues")
                    url.set("https://github.com/MoviebaseApp/${project.name}/issues")
                }
                scm {
                    connection.set("scm:git:https://github.com/MoviebaseApp/${project.name}.git")
                    developerConnection.set("scm:git:git@github.com:MoviebaseApp/${project.name}.git")
                    url.set("https://github.com/MoviebaseApp/${project.name}")
                }
            }
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
}
