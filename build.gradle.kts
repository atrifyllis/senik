import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import dev.monosoul.jooq.RecommendedVersions


@Suppress(
    "DSL_SCOPE_VIOLATION"
)
plugins {
    alias(libs.plugins.springBoot)
    alias(libs.plugins.springDepManagement)
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinPluginSpring)
    alias(libs.plugins.kotlinPluginJpa)
    alias(libs.plugins.gradlePrettyLogger)
    alias(libs.plugins.sonarqube)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.openapi)
    alias(libs.plugins.versionChecker)
    alias(libs.plugins.kover)
//    alias(libs.plugins.native)
//    alias(libs.plugins.jooq)
    alias(libs.plugins.jooqDocker)

    jacoco
}

group = "gr.senik"
version = "0.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    mavenLocal()
}

extra["testcontainersVersion"] = "1.17.6"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.jooq:jooq:${RecommendedVersions.JOOQ_VERSION}")




    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.flywaydb:flyway-core")
    implementation("org.postgresql:postgresql")

    implementation(libs.oktaSpring)
    implementation(libs.hibernateTypes)
    implementation(libs.kotlinLogging)
    implementation(libs.mapStruct)

    implementation(libs.bundles.jmolecules)
    implementation(libs.bundles.openApi)
    implementation(libs.bundles.ff4j)

    jooqCodegen("org.postgresql:postgresql")


    // check settings.gradle.kts to see how we import this module. it is a composite build, so we use it here like a normal library dependency!
    implementation("gr.alx:common")


    runtimeOnly("org.postgresql:postgresql")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    kapt(libs.mapStructProcessor)

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testImplementation(libs.kafkaTestContainers)
    testImplementation(libs.bundles.archUnit)

}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true) // enable xml reports required by sonarqube
    }
}

tasks.sonarqube {
    dependsOn(tasks.test)
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

configurations {
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
}

tasks.generateJooqClasses {

    basePackageName.set("gr.senik.common.adapters.secondary.persistence")
    outputDirectory.set(project.layout.buildDirectory.dir("generated-sources"))
    usingJavaConfig {
        name = "org.jooq.codegen.KotlinGenerator"
    }

}
/*
jooq {
    // use jOOQ version defined in Spring Boot
    version.set(dependencyManagement.importedProperties["jooq.version"])
    edition.set(nu.studer.gradle.jooq.JooqEdition.OSS)
    configurations {
        create("main") {
            jooqConfiguration.apply {

                jdbc.apply {
                    // Configure the database connection here (see gradle.properties)
                    driver = project.properties["driverClassName"].toString()
                    url = project.properties["url"].toString()
                    user = project.properties["username"].toString()
                    password = project.properties["password"].toString()
                }

                generator.apply {
                    // The default code generator.
                    // You can override this one, to generate your own code style.

                    // Supported generators:
                    //  - org.jooq.codegen.JavaGenerator
                    //  - org.jooq.codegen.ScalaGenerator
                    //  - org.jooq.codegen.KotlinGenerator

                    // Defaults to org.jooq.codegen.JavaGenerator
                    name = "org.jooq.codegen.KotlinGenerator"

                    database.apply {
                        // The database type. The format here is:
                        // org.jooq.meta.[database].[database]Database
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        // The database schema (or in the absence of schema support, in your RDBMS this
                        // can be the owner, user, database name) to be generated. This cannot be combined with the <schemata/> element.
                        // If <inputSchema/> is missing then all schemas will be considered.
                        inputSchema = "public"

                        // All elements that are generated from your schema
                        // (A Java regular expression. Use the pipe to separate several expressions)
                        // Watch out for case-sensitivity. Depending on your database, this might be important!
                        // You can create case-insensitive regular expressions using this syntax: (?i:expr).
                        // Whitespace is ignored and comments are possible.
                        includes = ".*"

                        // All elements that are excluded from your schema
                        // (A Java regular expression. Use the pipe to separate several expressions).
                        // Excludes match before includes, i.e. excludes have a higher priority.
                        excludes = """
                                  flyway_schema_history 
                                  """

                        // A custom version number that, if available, will be used to assess whether the {@link #getInputSchema()} will need to be regenerated.
                        schemaVersionProvider = "SELECT MAX(\"version\") FROM \"flyway_schema_history\""

                    }

                    generate.apply {
//                        isDeprecated = true
                        isRecords = true
//                        isDaos = true
//                        isValidationAnnotations = true
//                        isSpringAnnotations = true
                    }

                    // uncomment if we want custom names for generated pojos/daos
                    *//* strategy.withMatchers(
                         org.jooq.meta.jaxb.Matchers()
                         .withTables(arrayOf(
                             org.jooq.meta.jaxb.MatchersTableType()
                                 .withPojoClass(
                                     org.jooq.meta.jaxb.MatcherRule()
                                     .withExpression("Jooq_$0")
                                     .withTransform(org.jooq.meta.jaxb.MatcherTransformType.PASCAL)),
                             org.jooq.meta.jaxb.MatchersTableType()
                                 .withDaoClass(
                                     org.jooq.meta.jaxb.MatcherRule()
                                     .withExpression("$0_Repository")
                                     .withTransform(org.jooq.meta.jaxb.MatcherTransformType.PASCAL))
                         ).toList()))*//*

                    target.apply {
                        packageName = "gr.senik.common.adapters.secondary.persistence"
                        directory = "build/generated-sources"
                    }
                }

            }
        }
    }
}


// Configure jOOQ task such that it only executes when something has changed
// that potentially affects the generated JOOQ sources:
// - the jOOQ configuration has changed (Jdbc, Generator, Strategy, etc.)
// - the classpath used to execute the jOOQ generation tool has changed
//   (jOOQ library, database driver, strategy classes, etc.)
// - the schema files from which the schema is generated and which is
//   used by jOOQ to generate the sources have changed (scripts added, modified, etc.)
tasks.named<nu.studer.gradle.jooq.JooqGenerate>("generateJooq") {
    // ensure database schema has been prepared by Flyway before generating the jOOQ sources
//    dependsOn("flywayMigrate")

    // declare Flyway migration scripts as inputs on the jOOQ task
    inputs.files(fileTree("${rootDir}/src/main/resources/db/migration"))
        .withPropertyName("migrations")
        .withPathSensitivity(PathSensitivity.RELATIVE)

    // make jOOQ task participate in incremental builds and build caching
    allInputsDeclared.set(true)
    outputs.cacheIf { true }
}*/

tasks.processResources { filesMatching("**/application.yaml") { expand(project.properties) } }

