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
        // ← Add this
//        property(
//            "sonar.coverage.jacoco.xmlReportPaths",
//            "${project.projectDir}/app/build/reports/jacoco/jacocoUnitTestReport/jacocoUnitTestReport.xml" //no path eror but files ere reported missing
//            "${layout.buildDirectory.get()}/reports/jacoco/jacocoUnitTestReport/jacocoUnitTestReport.xml" //reported path errorerror

//        )
    }
}
