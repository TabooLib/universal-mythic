import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

dependencies {
    implementation(project(":project:common"))
    implementation(project(":project:implementation-v4"))
    implementation(project(":project:implementation-v5"))
}

tasks {
    withType<ShadowJar> {
        archiveClassifier.set("")
        archiveBaseName.set(rootProject.name)
    }
    build {
        dependsOn(shadowJar)
    }
}