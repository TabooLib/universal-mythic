import io.izzel.taboolib.gradle.*

val taboolib_version: String by project

plugins {
    id("io.izzel.taboolib") version "2.0.18"
}

taboolib {
    subproject = true
    env {
        // install(UNIVERSAL, BUKKIT_ALL, NMS_UTIL)
        install(Basic, Bukkit, BukkitNMS, BukkitNMSUtil, BukkitUtil)
    }
    version {
        taboolib = taboolib_version
    }
}

dependencies {
    api(project(":project:common"))
    compileOnly("com.electronwill.night-config:core-conversion:6.0.0")
    compileOnly("com.electronwill.night-config:core:3.6.6")
    // 你改你妈个蛋，我爱说实话
    // compileOnly("public:MythicMobs5:5.0.4")
    compileOnly(fileTree("libs"))
    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("org.yaml:snakeyaml:1.26")
    compileOnly("ink.ptms.core:v11802:11802-minimize:mapped")
    compileOnly("ink.ptms.core:v11802:11802-minimize:universal")
}

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
