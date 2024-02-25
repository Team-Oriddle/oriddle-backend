import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	id("com.google.cloud.tools.jib") version "3.4.0"
	kotlin("jvm") version "1.9.20"
	kotlin("plugin.spring") version "1.9.20"
	kotlin("plugin.jpa") version "1.9.20"
}

group = "com.tuk"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-websocket")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// security
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.security:spring-security-test")

	// Swagger
	implementation("org.springdoc:springdoc-openapi:2.3.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

	runtimeOnly("com.h2database:h2")
	runtimeOnly("mysql:mysql-connector-java:8.0.33")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

jib {
	from {
		image = "openjdk:17-slim"
		auth {
			username = project.findProperty("DOCKER_ID") as String?
			password = project.findProperty("DOCKER_PASSWORD") as String?
		}
	}
	to {
		image = "${project.findProperty("DOCKER_ID")}/${project.findProperty("DOCKER_IMAGE_NAME")}"
		auth {
			username = project.findProperty("DOCKER_ID") as String?
			password = project.findProperty("DOCKER_PASSWORD") as String?
		}
		tags = setOf("latest")
	}
	container {
		jvmFlags = listOf("-Xms128m", "-Xmx128m")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
