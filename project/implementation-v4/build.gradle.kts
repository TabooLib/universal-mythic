val taboolib_version: String by project

plugins {
    id("io.izzel.taboolib") version "1.55"
}

taboolib {
    install("common")
    install("module-configuration")
    install("platform-bukkit")
    options("skip-minimize", "keep-kotlin-module", "skip-kotlin-relocate", "skip-taboolib-relocate")
    classifier = null
    version = taboolib_version
    exclude("taboolib")
}

dependencies {
    api(project(":project:common"))
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