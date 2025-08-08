import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.gradle.java")
    id("org.gradle.maven-publish")
    id("org.jetbrains.kotlin.jvm") version "1.9.22" apply false
}

subprojects {
    apply<JavaPlugin>()
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
    }
    dependencies {
        "compileOnly"(kotlin("stdlib"))
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjvm-default=all")
        }
    }
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}

publishing {
    repositories {
        maven {
            url = uri("https://nexus.maplex.top/repository/maven-releases/")
            isAllowInsecureProtocol = true
            credentials {
                username = project.findProperty("NEXUS_MAPLEX_USERNAME")?.toString() ?: System.getenv("NEXUS_MAPLEX_USERNAME")
                password = project.findProperty("NEXUS_MAPLEX_PASSWORD")?.toString() ?: System.getenv("NEXUS_MAPLEX_PASSWORD")
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            artifactId = "um"
            groupId = "ink.ptms"
            version = project.version.toString()
            artifact(File("plugin/build/libs/${rootProject.name}-${project.version}.jar"))
        }
    }
}

gradle.buildFinished {
    buildDir.deleteRecursively()
}
