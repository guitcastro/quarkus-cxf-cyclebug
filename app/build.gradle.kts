import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project
val picocontainerVersion: String by project

plugins {
    id("org.jetbrains.kotlin.plugin.allopen").version("1.8.20")
    id("io.quarkus")
    id("com.github.davidmc24.gradle.plugin.avro").version("1.3.0")
}

repositories {
    maven {
        url = uri("https://packages.confluent.io/maven/")
    }
    mavenCentral()
    mavenLocal()
}

project.ext.set("avroVersion", "1.11.0")
dependencies {
    implementation(project(":domain"))
    implementation(project(":infrastructure"))
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))

    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy-reactive")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")

    implementation("io.quarkus:quarkus-arc")

    implementation("io.arrow-kt:arrow-core:1.0.1")

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.quarkiverse.cucumber:quarkus-cucumber:0.5.0")
    testImplementation("io.cucumber:cucumber-picocontainer:${picocontainerVersion}")

    testImplementation(testFixtures(project(":infrastructure")))

}

allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

val compileKotlin: KotlinCompile by tasks
val compileTestKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
    javaParameters = true
}

compileTestKotlin.kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
}
