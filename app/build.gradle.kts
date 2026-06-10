plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.klint)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.hilt) // Hilt plugin
    alias(libs.plugins.ksp)
//    jacoco
}

ktlint {
    version = "0.50.0"
    android = true

    // fail build if issues found
    ignoreFailures = false

    // show output in console
    outputToConsole = true

    // optional: filter files
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}

tasks.named("preBuild") {
    dependsOn("ktlintFormat")
}

hilt {
    enableAggregatingTask = false
}

android {
    namespace = "com.fav.atrefo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.fav.atrefo"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        getByName("debug") {
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

// jacoco {
//    toolVersion = libs.versions.jacoco.get()
// }

// configurations.all {
//    resolutionStrategy.force("com.squareup:javapoet:1.13.0")
// }

tasks.register<JacocoReport>("jacocoFullReport") {
    group = "Reporting"
    description = "Merged unit + instrumented coverage report"

    dependsOn("testDebugUnitTest", "createDebugCoverageReport")
//    dependsOn("testDebugUnitTest")

    reports {
        xml.required.set(true) // for CI upload (Codecov, Coveralls, etc.)
        html.required.set(true) // for local browser viewing
    }

    val fileFilter = listOf(
        // Android generated
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*",
        "android/**/*.*",
        "**/AtrifoApp*",
        "**/*\$default*", // Kotlin default parameter bridges
        "**/MainActivityKt*", // top-level composables file

        // Hilt generated — all patterns needed
        "**/Dagger*.*", // ← catches DaggerAtrifoApp_HiltComponents_*
        "**/*_HiltComponents*.*", // ← catches AtrifoApp_HiltComponents_*
        "**/*HiltComponents*.*",
        "**/*_HiltModules*.*",
        "**/*Hilt_*.*",
        "**/hilt_aggregated_deps/**",
        "**/*ComponentTreeDeps*.*", // ← catches AtrifoApp_ComponentTreeDeps
        "**/AtrifoApp_*.*", // ← catches all AtrifoApp_ generated classes
        "**/DaggerAtrifoApp*.*",

        // Firebase generated
        "**/FirebaseModule*.*",
        "**/*_Provide*.*", // ← catches FirebaseModule_ProvideFirebase*

        // Composable singletons (Compose compiler generated)
        "**/ComposableSingletons*.*",

        // Data binding
        "**/databinding/**",
        "**/BR.*",

        // Room
        "**/*_Impl*.*",

        // Dependency injection components
        "**/dagger/**",

        // Compose Theme
        "app/src/main/java/**/ui/theme/**",

    )

    val javaDebugTree = fileTree("${layout.buildDirectory.get()}/intermediates/javac/debug") {
        exclude(fileFilter)
    }
    val kotlinDebugTree = fileTree("${layout.buildDirectory.get()}/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }

// classDirectories = "what code are we measuring?"  →  main app source only
// executionData    = "what tests ran?"              →  unit tests + instrumented tests

// executionData = the tests that ran (unit + instrumented)
// classDirectories = your app code being measured
// sourceDirectories = where to find the source for the HTML report

    sourceDirectories.setFrom(files("src/main/java", "src/main/kotlin"))
    classDirectories.setFrom(files(javaDebugTree, kotlinDebugTree))
    executionData.setFrom(
        fileTree(layout.buildDirectory.get()) {
            include(
//                "jacoco/testDebugUnitTest.exec", //old compilers need this
                "outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec",
                "outputs/code_coverage/debugAndroidTest/connected/**/*.ec",
            )
        },
    )
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    // Firebase
    // Import the Firebase BoM (manages all versions)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.database)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler) // Use ksp (preferred) or kapt
    implementation(libs.hilt.navigation.compose) // Only if using Compose

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
