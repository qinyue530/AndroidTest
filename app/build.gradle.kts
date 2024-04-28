plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.amdroidtestjava"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.amdroidtestjava"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions{
            annotationProcessorOptions {
                argument("room.schemaLocation","$projectDir/schema".toString()) //指定数据库生成的schema 导出的位置
            }
        }
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.butterknife)
    // https://mvnrepository.com/artifact/androidx.room/room-compiler
    annotationProcessor(libs.room.compiler)

    // https://mvnrepository.com/artifact/androidx.room/room-runtime
    implementation(libs.room.runtime)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
