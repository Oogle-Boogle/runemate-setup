import java.net.URL

plugins {
    id("java")
    id ("com.runemate") version "1.3.0"
    id ("io.freefair.lombok") version "8.6"
}

group = "com.runemate.test"
version = "1.0-SNAPSHOT"

fun getLatestApiVersion(): String {
    val url = "https://gitlab.com/api/v4/projects/32972353/packages/maven/com/runemate/runemate-game-api/maven-metadata.xml"
    val content = URL(url).readText()
    return content.substringAfter("<latest>").substringBefore("</latest>")
}

fun getLatestClientVersion(): String {
    val url = "https://gitlab.com/api/v4/projects/10471880/packages/maven/com/runemate/runemate-client/maven-metadata.xml"
    val content = URL(url).readText()
    return content.substringAfter("<latest>").substringBefore("</latest>")
}

runemate {
    devMode = true
    autoLogin = true
    apiVersion = getLatestApiVersion()
    clientVersion = getLatestClientVersion()
    debug = true
    allowExternalDependencies = true
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
