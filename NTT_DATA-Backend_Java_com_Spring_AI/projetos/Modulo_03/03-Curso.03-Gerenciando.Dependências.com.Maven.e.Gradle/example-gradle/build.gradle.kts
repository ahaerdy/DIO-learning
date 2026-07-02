plugins {
    id("java")
}

group = "br.com.dio"
version = "1.0-SNAPSHOT"

val mapstructVersion = "1.5.5.Final"
val lombokVersion = "1.18.30"
val lombokMapstructBinding = "0.2.0"

repositories {
    mavenCentral()
}

dependencies {
    // Dependências principais
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    implementation("org.projectlombok:lombok-mapstruct-binding:$lombokMapstructBinding")

    // Lombok apenas em tempo de compilação
    compileOnly("org.projectlombok:lombok:$lombokVersion")

    // Processadores de anotação necessários para gerar código
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:$lombokMapstructBinding")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
}
