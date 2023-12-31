plugins {
  kotlin("jvm") version "1.9.21"
}

group = "org.javakotlin"
version = "1.0"


repositories {
  mavenCentral()
}

dependencies {
  testImplementation("org.jetbrains.kotlin:kotlin-test")

  // https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
  implementation("com.mysql:mysql-connector-j:8.2.0")
}

tasks.test {
  useJUnitPlatform()
}
kotlin {
  jvmToolchain(21)
}

// agrego la interoperatividad con java
sourceSets {
  main {
    java {
      srcDirs("src/main/java")
    }
    kotlin {
      srcDirs("src/main/kotlin")
    }
  }
}

// Define la clase principal para el .jar
tasks.jar {
  manifest {
    attributes["Main-Class"] = "org.javakotlin.MainKt"
  }
  from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
