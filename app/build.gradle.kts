import java.util.Properties

val localProperties = Properties().also {
    it.load(project.rootProject.file("local.properties").inputStream())
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}

android {
    namespace = "memory.fabricators.snapfit"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "memory.fabricators.snapfit"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField(
            type = "String",
            name = "BASE_URL",
            value = localProperties.getProperty("BASE_URL"),
        )
        buildConfigField(
            type = "String",
            name = "KAKAO_API_KEY",
            value = localProperties.getProperty("KAKAO_API_KEY"),
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.bundles.essentials)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.bundles.compose)
    implementation(libs.bundles.coil)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.orbit)
    implementation(libs.bundles.ktor)

    implementation("com.kakao.sdk:v2-user:2.12.1")

    implementation(libs.room)
    ksp(libs.room.compiler)

    testImplementation(libs.bundles.unitTests)
    androidTestImplementation(libs.bundles.androidTests)
    androidTestImplementation(libs.bundles.composeTests)
}