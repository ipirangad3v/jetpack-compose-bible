// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
    dependencies {
        classpath(BuildPlugins.androidGradle)
        classpath(BuildPlugins.googleServices)
        classpath(BuildPlugins.firebaseCrashlytics)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        classpath(BuildPlugins.playPublisher)
        classpath(BuildPlugins.kotlin)
        classpath(BuildPlugins.firebaseAppDistribution)
        classpath(BuildPlugins.firebasePerf)
        classpath(BuildPlugins.kotlinGradle)
        //Serialization
        classpath(BuildPlugins.kotlinXSerializationGradle)

        //Firebase
        //classpath 'com.google.gms:google-services:4.3.13'
        // classpath 'com.google.firebase:firebase-crashlytics-gradle:2.9.1'
        // classpath 'com.google.firebase:perf-plugin:1.4.1'

        //Klint
        classpath(BuildPlugins.klintGradle)

        //app distribution
//        classpath 'com.google.firebase:firebase-appdistribution-gradle:3.0.2'
//
//        classpath("com.github.triplet.gradle:play-publisher:4.0.0-SNAPSHOT")
    }
}
plugins {
    id(BuildPlugins.hiltPlugin) version Versions.hilt apply false
    id(BuildPlugins.kotlinAndroidGradle) version Versions.kotlin apply false
}
allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}
configurations.all {
    if (!name.startsWith("ktlint")) {
        resolutionStrategy {
            eachDependency {
                // Force Kotlin to our version
                if (requested.group == "org.jetbrains.kotlin") {
                    useVersion(Versions.kotlin)
                }
            }
        }
    }
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}
