// Gradle plugins this module needs.
// They add more gradle tasks and settings to gradle.
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

// Android related settings. We can use this, because
// the module applies the Android Gradle plugin
android {
    // base package your code lives in
    namespace = "com.kit.examples"
    // Android version that is used to build your app.
    compileSdk = 34

    defaultConfig {
        // Unique identifier of your app. If you uploaded your app to the Play Store once, you can't change it
        applicationId = "com.kit.examples"
        // Minimum supported Android version. Devices that run lower versions will not see your
        // app in the Play Store.
        minSdk = 24
        // Highest Android version which you tested your app with
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // This block is needed to be able to use vector graphics
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    // You can build your app in different variants, like e.g.
    // in release variant, where you obfuscate and minify the code.
    // Or in debug variant, where you e.g. want to add "-debug" to the
    // application id and name, to distinguish them.
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
        // Describes which java features you can use in your code.
        // Here you can use all features that have been introduced
        // in Java 17 or in a previous version.
        sourceCompatibility = JavaVersion.VERSION_17

        // Describes the java version of the output / bytecode.
        // Theoretically, this means, that the app can only be executed on devices which have
        // at least this version installed. But Android uses desugaring to make it compatible for
        // devices with lower java versions (https://developer.android.com/studio/write/java8-support-table)
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        // Kotlin code compiles to java bytecode.
        // With this we set, which version the bytecode has.
        jvmTarget = "17"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Here you can add third party libraries.
// We could add them directly like the first one or
// reference them from our version catalog like all the others.
// (which in the end does the same, it's just more maintainable).
dependencies {
    // Here we directly enter the id of the library.
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    // Here we use our version catalog.
    implementation(libs.lifecycle.viewmodel.ktx)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.material.old)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
