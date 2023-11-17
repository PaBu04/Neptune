// Top-level build file where you can add configuration options common to all sub-projects/modules.
// These are plugins for gradle. They extend gradle with new tasks.
// We can define them here, then every module can use them.
plugins {
    // Here we add the android gradle plugin (AGP)
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
}
