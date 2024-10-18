import io.izzel.taboolib.gradle.Basic
import io.izzel.taboolib.gradle.Bukkit
import io.izzel.taboolib.gradle.BukkitUtil

val taboolib_version: String by project

plugins {
    id("io.izzel.taboolib") version "2.0.18"
}

taboolib {
    subproject = true
    env {
//        install(UNIVERSAL, BUKKIT_ALL)
        install(Basic, Bukkit, BukkitUtil)
    }
    version {
        taboolib = taboolib_version
    }
}

dependencies {
    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("com.electronwill.night-config:core-conversion:6.0.0")
    compileOnly("org.yaml:snakeyaml:1.26")
    compileOnly("ink.ptms.core:v11802:11802-minimize:mapped")
    compileOnly("ink.ptms.core:v11802:11802-minimize:universal")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}