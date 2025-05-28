import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

val clientPropertiesFile = rootProject.file("client.properties")
val clientProperties = Properties()
clientProperties.load(FileInputStream(clientPropertiesFile))

android {
    namespace = "com.levox.game_archive"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        if (clientPropertiesFile.exists()) {
            buildConfigField(
                "String",
                "CLIENT_SECRET",
                clientProperties["CLIENT_SECRET"] as String
            )
        }

        buildConfigField(
            "String",
            "CLIENT_ID",
            project.properties["CLIENT_ID"] as String
        )

        buildConfigField(
            "String",
            "REDIRECT_URI",
            project.properties["REDIRECT_URI"] as String
        )

        buildConfigField(
            "String",
            "AUTH_BASE_URL",
            project.properties["AUTH_BASE_URL"] as String
        )

        buildConfigField(
            "String",
            "BASE_URL",
            project.properties["BASE_URL"] as String
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
}