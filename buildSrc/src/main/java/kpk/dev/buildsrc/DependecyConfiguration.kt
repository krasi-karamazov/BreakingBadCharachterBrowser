package kpk.dev.buildsrc

data class DependecyConfiguration(val config: ConfigurationType,
                                  val dependency: String,
                                  val isProject:Boolean = false)

enum class ConfigurationType(val value: String) {
    IMPLEMENTATION("implementation"),
    TEST_IMPLEMENTATION("testImplementation"),
    ANDROID_TEST_IMPLEMENTATION("androidTestImplementation"),
    KAPT("kapt"),
    API("api")
}