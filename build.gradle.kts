plugins {
  kotlin("jvm") version "1.9.21"
}

group = "org.javakotlin"
version = "1.0-SNAPSHOT"


repositories {
  mavenCentral()
}

dependencies {
  testImplementation("org.jetbrains.kotlin:kotlin-test")
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

tasks.jar {
  manifest {
    attributes["Main-Class"] = "org.javakotlin.MainKt"
  }
}
