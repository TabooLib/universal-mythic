import io.izzel.taboolib.gradle.BUKKIT_ALL
import io.izzel.taboolib.gradle.NMS_UTIL
import io.izzel.taboolib.gradle.UNIVERSAL

val taboolib_version: String by project

plugins {
    id("io.izzel.taboolib") version "2.0.0"
}

taboolib {
    subproject = true
    env {
        install(UNIVERSAL, BUKKIT_ALL, NMS_UTIL)
    }
}

dependencies {
    api(project(":project:common"))
    compileOnly("com.electronwill.night-config:core-conversion:6.0.0")
    compileOnly("com.electronwill.night-config:core:3.6.6")
    compileOnly("public:MythicMobs:1.0.1")
    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("org.yaml:snakeyaml:1.26")
    compileOnly("ink.ptms.core:v11802:11802-minimize:mapped")
    compileOnly("ink.ptms.core:v11802:11802-minimize:universal")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
