plugins {
    id("org.jetbrains.kotlin.plugin.allopen").version("1.8.20")
    id ("org.kordamp.gradle.jandex") version "0.12.0"
    id("io.quarkus")
    id ("java-test-fixtures")
//    id("uk.co.boothen.gradle.wsimport") version "0.18"
}

//wsimport {
//    wsdl("service.wsdl")
//}

repositories {
    mavenCentral()
}

allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.persistence.Entity")
}

tasks.compileKotlin {
//    dependsOn("wsImport1")
}

tasks.quarkusDependenciesBuild {
    dependsOn("jandex")
}

tasks.compileTestKotlin {
    dependsOn("jandex")
}

tasks.jacocoTestReport {
    dependsOn("jandex")
}

dependencies {
    implementation(project(":domain"))

    implementation("io.quarkiverse.cxf:quarkus-cxf:2.0.2")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    implementation("io.quarkus:quarkus-liquibase")
    implementation("io.quarkus:quarkus-jdbc-postgresql")

    implementation("com.sun.xml.ws:rt:4.0.1")
//    implementation("io.quarkus:quarkus-reactive-pg-client")

    val quarkusPlatformGroupId: String by project
    val quarkusPlatformArtifactId: String by project
    val quarkusPlatformVersion: String by project
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.quarkus:quarkus-jdbc-h2")

    testFixturesApi(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    testFixturesApi("io.quarkus:quarkus-test-common")
    testFixturesApi("org.testcontainers:cockroachdb:1.17.6")
    testFixturesApi("com.github.tomakehurst:wiremock:2.27.2")
}
