import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.3.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
	kotlin("plugin.jpa") version "1.3.72"
	kotlin("kapt") version "1.3.61"
	id("org.siouan.frontend") version "3.0.1"
	kotlin("plugin.allopen") version "1.3.61"
}

group = "com.softwaresale"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

extra["azureVersion"] = "2.3.3"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.microsoft.azure:azure-active-directory-spring-boot-starter")
	implementation("com.microsoft.azure:azure-keyvault-secrets-spring-boot-starter")
	implementation("com.microsoft.azure:azure-spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	kapt("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("org.springframework.security:spring-security-test")
	// YAML support
	implementation("org.yaml:snakeyaml:1.23")
}

tasks.withType<ProcessResources> {
	from("src/main/web/opus-ng/dist/opus-ng") {
		into("static/")
	}
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}

frontend {
	packageJsonDirectory.set(projectDir.resolve("src/main/web/opus-ng"))
	nodeDistributionProvided.set(false)
	nodeVersion.set("12.18.2")
	yarnEnabled.set(true)
	yarnVersion.set("1.22.4")
	installScript.set("install")
	cleanScript.set("run clean")
	checkScript.set("test")
	assembleScript.set("ng build --prod")
}

dependencyManagement {
	imports {
		mavenBom("com.microsoft.azure:azure-spring-boot-bom:${property("azureVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}
