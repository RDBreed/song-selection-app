import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
  id("java")
  id("org.openapi.generator") version "7.8.0"
  id("org.springframework.boot") version "3.3.4"
  id("io.spring.dependency-management") version "1.1.6"
}

group = "com.fedex.cap"
version = "1.0-SNAPSHOT"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

repositories {
  mavenCentral()
}

fun DependencyHandler.wiremockImplementation(dependencyNotation: Any): Dependency? =
  add("wiremockImplementation", dependencyNotation)

sourceSets.create("wiremock") {
  java.srcDirs("src/wiremock/java", "${project.layout.buildDirectory.get()}/wiremock/src/main/java")
}


dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.apache.commons:commons-csv:1.8")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("io.jsonwebtoken:jjwt-api:0.12.6")  // JJWT API
  implementation("io.jsonwebtoken:jjwt-impl:0.12.6") // JJWT Implementation
  implementation("io.jsonwebtoken:jjwt-jackson:0.12.6") // For JSON parsing using Jackson
  runtimeOnly("com.h2database:h2")

  wiremockImplementation("jakarta.annotation:jakarta.annotation-api:3.0.0")
  wiremockImplementation("org.wiremock:wiremock-standalone:3.9.1")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
  useJUnitPlatform()
}

tasks.register("generateWiremockApi", GenerateTask::class) {
  generatorName.set("java-wiremock")
  inputSpec.set("$rootDir/src/main/openapi/song-api.yml")
  outputDir.set("${project.layout.buildDirectory.get()}/wiremock")
  additionalProperties.set(mapOf("useTags" to true))
  apiPackage.set("eu.phaf.song.api.wiremock")
  ignoreFileOverride.set("$projectDir/.openapi-generator-ignore")
  configOptions.set(
    mapOf(
      "reactive" to "true",
      "java8" to "true",
      "interfaceOnly" to "true",
      "skipDefaultInterface" to "true",
      "library" to "webclient",
      "serializationLibrary" to "jackson",
      "useJakartaEe" to "true",
      "openApiNullable" to "false",
      "enumUnknownDefaultCase" to "true",
    )
  )
}

tasks.register("generateWiremockAdminApi", GenerateTask::class) {
  generatorName.set("java-wiremock")
  inputSpec.set("$rootDir/src/main/openapi/song-admin-api.yml")
  outputDir.set("${project.layout.buildDirectory.get()}/wiremock")
  additionalProperties.set(mapOf("useTags" to true))
  apiPackage.set("eu.phaf.song.api.wiremock.admin")
  ignoreFileOverride.set("$projectDir/.openapi-generator-ignore")
  configOptions.set(
    mapOf(
      "reactive" to "true",
      "java8" to "true",
      "interfaceOnly" to "true",
      "skipDefaultInterface" to "true",
      "library" to "webclient",
      "serializationLibrary" to "jackson",
      "useJakartaEe" to "true",
      "openApiNullable" to "false",
      "enumUnknownDefaultCase" to "true",
    )
  )
}

tasks.compileJava {
  dependsOn("generateWiremockApi")
  dependsOn("generateWiremockAdminApi")
}
