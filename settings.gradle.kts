pluginManagement {
    val quarkusPluginId: String by settings
    val quarkusPluginVersion: String by settings
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id(quarkusPluginId).version(quarkusPluginVersion) apply false
        kotlin("jvm") version "1.8.20" apply false
        jacoco apply false
        id("io.gitlab.arturbosch.detekt").version("1.20.0-RC1") apply false
    }
}

buildCache {
    remote<HttpBuildCache> {
        url = uri("http://gradle-cache-node.gradle-cache-node.svc.cluster.local:5071/cache/")
        isAllowInsecureProtocol = true
        isPush = true
    }
}

rootProject.name = "cxfCycle"
include("app")
include("infrastructure")
include("domain")
