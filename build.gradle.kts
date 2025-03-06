plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependencyManagement)
}

group = "com.enzulode.stats"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // general
    implementation(platform(libs.dep.bom.springCloud))
    implementation(platform(libs.dep.bom.awssdk))
    // k8s
    implementation(libs.dep.spring.starter.k8sServiceDiscovery)

    // observability
    implementation(libs.dep.spring.starter.actuator)

    // specific
    // util
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // api
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

    // persistence (database)
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("io.github.pelenthium:clickhouse-dialect-spring-boot-starter:1.2.0")
    runtimeOnly("com.clickhouse:clickhouse-jdbc:0.8.1")
    implementation("org.flywaydb:flyway-core")
    runtimeOnly("org.flywaydb:flyway-database-clickhouse:10.17.0")
    implementation("org.lz4:lz4-java:1.8.0")

    // security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("com.jayway.jsonpath:json-path")

    // distributed system
    implementation("org.springframework.boot:spring-boot-starter-amqp")
}

tasks.named("bootBuildImage", org.springframework.boot.gradle.tasks.bundling.BootBuildImage::class) {
    val repoOwnerAndName = System.getenv("GITHUB_ORG_REPO") as String
    val runId = System.getenv("GITHUB_SHA") as String
    imageName = "cr.selcloud.ru/${repoOwnerAndName}:${runId}"
    imagePlatform = "linux/amd64"
}
