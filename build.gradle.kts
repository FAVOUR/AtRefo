// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    configurations.classpath {
        resolutionStrategy {
            force("org.apache.commons:commons-compress:1.27.1")
        }
    }
}




plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.klint) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.firebase.crashlytics)  apply false
    alias(libs.plugins.sonarqube.cloud)

//    jacoco
//    alias(libs.plugins.hilt) apply false


}



sonar {

    properties {
        property("sonar.projectKey", "FAVOUR_AtRefo")
        property("sonar.organization", "favour")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.exclusions", "**/*.webp,**/*.png,**/*.jpg,**/*.jpeg")
        property("sonar.sources", "app/src/main")
        property("sonar.tests", "app/src/test,app/src/androidTest")

//        property("sonar.sources", "app/src/main")

        property("sonar.java.binaries", "app/build/intermediates/javac/debug")

        // ← Add this
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "${rootProject.projectDir}/app/build/reports/jacoco/jacocoFullReport/jacocoFullReport.xml")
        property("sonar.androidLint.reportPaths", "no-lint-report")
//        sonar.androidLint.reportPaths=app/build/reports/lint-results-debug.xml

    }
}
