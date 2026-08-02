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

composeCompiler {
    // Every composable otherwise gets a pair of `if (isTraceInProgress())` guards that
    // only fire when a composition tracer is attached — i.e. never under test. They are
    // generated branches nothing can reach, so they only distort branch coverage.
    includeTraceMarkers = false
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
    testOptions {
        unitTests {
            // Android framework stubs return defaults instead of throwing, so plain
            // JVM tests can exercise code that merely touches framework types.
            isReturnDefaultValues = true
        }
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

    dependsOn("testDebugUnitTest", "createDebugCoverageReport", "transformDebugClassesWithAsm")
//    dependsOn("testDebugUnitTest")

    reports {
        xml.required.set(true) // for CI upload (Codecov, Coveralls, etc.)
        html.required.set(true) // for local browser viewing
    }

    // Only genuinely generated code belongs here. Anything hand-written stays in the
    // denominator — if it is worth writing, it is worth covering.
    //
    // Patterns must be anchored so they cannot swallow the sources they sit next to:
    // "AtrifoApp_*" (generated), never "AtrifoApp*" (which also eats AtrifoApp.kt);
    // "*_Provide*" (generated factories), never "FirebaseModule*" (which also eats
    // the hand-written @Provides bodies).
    val fileFilter = listOf(
        // Android build-generated
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",

        // Hilt / Dagger generated
        "**/AtrifoApp_*.*", // AtrifoApp_HiltComponents, _GeneratedInjector, _ComponentTreeDeps
        "**/Dagger*.*", // DaggerAtrifoApp_HiltComponents_SingletonC
        "**/Hilt_*.*", // Hilt_AtrifoApp
        "**/*_Provide*.*", // FirebaseModule_ProvideFirebaseAppFactory
        "**/hilt_aggregated_deps/**",
        "**/dagger/**",

        // @Preview scaffolding — hand-written, but never runs in production
        "**/ui/preview/**",
    )

    // Measure the classes that actually RAN, not the ones the compilers first emitted.
    //
    // Hilt rewrites @HiltAndroidApp classes after compilation: on disk in kotlin-classes
    // AtrifoApp extends Application, at runtime it extends Hilt_AtrifoApp. JaCoCo matches
    // execution data to classes by a CRC of their bytecode, so reading the pre-transform
    // tree makes the app class silently report 0% however hard you test it.
    //
    // transformDebugClassesWithAsm/dirs is the post-transform output and holds both the
    // Kotlin and the javac/KSP classes, so it replaces both trees.
    val runtimeClassTree =
        fileTree(
            "${layout.buildDirectory.get()}/intermediates/classes/debug/transformDebugClassesWithAsm/dirs",
        ) {
            exclude(fileFilter)
        }

// executionData = the tests that ran (unit + instrumented)
// classDirectories = your app code being measured
// sourceDirectories = where to find the source for the HTML report

    sourceDirectories.setFrom(files("src/main/java", "src/main/kotlin"))
    classDirectories.setFrom(files(runtimeClassTree))
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
    testImplementation(libs.mockk)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
