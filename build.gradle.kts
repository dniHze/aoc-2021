plugins {
    kotlin("jvm") version "1.6.0"
    id("application")
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("MainKt")
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }

        test {
            java.srcDirs("test")
        }
    }

    wrapper {
        gradleVersion = "7.3"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation("com.jakewharton.picnic:picnic:0.5.0")

    testImplementation("io.kotest:kotest-runner-junit5:5.0.1")
    testImplementation("io.kotest:kotest-assertions-core:5.0.1")
}