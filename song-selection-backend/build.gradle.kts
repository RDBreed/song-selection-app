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

dependencies {

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
  apiPackage.set("com.phaf.song.api.wiremock")
  invokerPackage.set("com.phaf.song.invoke.wiremock")
  modelPackage.set("com.phaf.song.domain.dto.wiremock")
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
  project.sourceSets.getByName("main").java.srcDir(outputDir)
}
