plugins {
    id(BuildPlugins.android)
    id(BuildPlugins.kotlinXSerialization)
//    id(BuildPlugins.crashlytics)
//    id(BuildPlugins.firebasePerformance)
//    id(BuildPlugins.gmsGooglePlayServices)
//    id(BuildPlugins.appDistribution)

//    id(BuildPlugins.tripletPlay)
    id(BuildPlugins.kotlinParcelize)
    kotlin("android")
    id(BuildPlugins.kotlinAndroid)
    kotlin(BuildPlugins.kapt)
    id(BuildPlugins.daggerHilt)
    id(BuildPlugins.klint)
}
android {
    compileSdk = ConfigData.compileSdkVersion
    buildToolsVersion = ConfigData.buildToolsVersion

    defaultConfig {
        applicationId = ConfigData.applicationId
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName
        testInstrumentationRunner = ConfigData.instrumentationRunner
        vectorDrawables.useSupportLibrary = ConfigData.useSupportLibrary
        multiDexEnabled = ConfigData.multiDexEnabled
    }

    buildTypes {
        named("debug") {
            isDebuggable = true
            buildConfigField("boolean", "DEBUG", "true")
        }

        named("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            isDebuggable = false
            buildConfigField("boolean", "DEBUG", "false")
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    dataBinding {
        enable = true
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources.excludes.add("META-INF/*")
    }
    namespace = ConfigData.applicationId
}
dependencies {

    implementation(Deps.coreKtx)
    implementation(Deps.lifecycleRuntime)
    implementation(Deps.activityCompose)
    testImplementation(Test.jUnit)
    androidTestImplementation(Test.testExt)

    // Kotlin Coroutines
    implementation(Deps.coroutinesCore)
    implementation(Deps.coroutinesAndroid)
    implementation(Deps.coroutinesPlayservice)
    implementation(Deps.workRuntime)

    // Room
    implementation(Deps.roomKtx)
    implementation(Deps.roomRuntime)
    kapt(Deps.sqliteJdbc)
    kapt(Deps.roomCompiler)

    // Navigation Compose
    implementation(Deps.navigationCompose)

    // Hilt
    implementation(Deps.hilt)
    kapt(Deps.hiltCompiler)
    implementation(Deps.hiltWorker)

    // Serialization
    implementation(Deps.kotlinSerialization)

    // Gson
    implementation(Deps.gson)

    // Retrofit
    implementation(Deps.retrofit)
    implementation(Deps.converterGson)
    implementation(Deps.loggingInterceptor)
    // Timber
    implementation(Deps.timber)

    // Compose
    implementation(Deps.composeRuntime)
    implementation(Deps.composeUi)
    implementation(Deps.composeMaterial)
    implementation(Deps.composeUiToolingPreview)
    debugImplementation(Deps.composeUiTooling)
    debugImplementation(Deps.composeUiTest)
    implementation(Deps.accompanist)

    // Firebase
//    implementation platform("com.google.firebase:firebase-bom:29.3.1")
//    implementation "com.google.firebase:firebase-analytics-ktx"
//    implementation "com.google.firebase:firebase-crashlytics-ktx"
//    implementation "com.google.firebase:firebase-perf-ktx"
//    implementation "com.google.firebase:firebase-database-ktx"
}

kapt {
    correctErrorTypes = true
}
