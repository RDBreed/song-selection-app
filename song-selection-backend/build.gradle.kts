import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
  id("java")
  id("org.openapi.generator") version "7.8.0"
}

group = "com.fedex.cap"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

fun DependencyHandler.wiremockImplementation(dependencyNotation: Any): Dependency? =
  add("wiremockImplementation", dependencyNotation)

sourceSets.create("wiremock") {
  java.srcDirs("src/wiremock/java", "${project.layout.buildDirectory.get()}/wiremock/src/main/java")
}


dependencies {
  wiremockImplementation("jakarta.annotation:jakarta.annotation-api:3.0.0")
  wiremockImplementation("org.wiremock:wiremock-standalone:3.9.1")
  testImplementation(platform("org.junit:junit-bom:5.10.0"))
  testImplementation("org.junit.jupiter:junit-jupiter")
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
