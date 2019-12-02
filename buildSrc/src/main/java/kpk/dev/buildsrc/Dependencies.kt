package kpk.dev.buildsrc

object ApplicationId {
    const val appId = "kpk.dev.gantask"
}

object Modules {
    const val app = ":app"
    const val network = ":common:network"
    const val presentation = ":common:presentation"
    const val disposable = ":common:disposable"
    const val caching = ":common:caching"
    const val feature_character_list = ":feature-character-list"
}

object AppVersion {
    const val versionCode = 1
    const val versionName = "1.0"
}

object DependenciesVersions {
    const val gradle = "3.5.2"
    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29

    const val appcompat = "1.1.0"
    const val design = "1.2.0-alpha02"
    const val cardview = "1.0.0"
    const val constraintLayout = "2.0.0-beta2"

    const val ktx = "1.2.0-rc01"

    const val kotlin = "1.3.61"
    const val rxjava = "2.2.15"
    const val rxkotlin = "2.4.0"
    const val rxPaper = "1.4.0"
    const val retrofit = "2.6.2"
    const val loggingInterceptor = "4.0.0"
    const val glide = "4.10.0"
    const val okHttp = "4.2.2"

    const val dagger = "2.23.1"
    const val javaxInject = "1"

    const val moshi = "1.9.2"
    const val lifecycle = "2.2.0-rc02"

    const val junit = "4.13-rc-2"
    const val assertjCore = "3.14.0"
    const val mockitoKotlin = "2.2.0"
    const val mockitoInline = "3.2.0"
    const val archCoreTesting = "2.1.0"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${DependenciesVersions.kotlin}"
    const val ktx = "androidx.core:core-ktx:${DependenciesVersions.ktx}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${DependenciesVersions.retrofit}"
    const val rxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${DependenciesVersions.retrofit}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${DependenciesVersions.retrofit}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${DependenciesVersions.loggingInterceptor}"
    const val glide = "com.github.bumptech.glide:glide:${DependenciesVersions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${DependenciesVersions.glide}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${DependenciesVersions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${DependenciesVersions.lifecycle}"
    const val dagger = "com.google.dagger:dagger:${DependenciesVersions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${DependenciesVersions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${DependenciesVersions.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${DependenciesVersions.dagger}"
    const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${DependenciesVersions.dagger}"
    const val javaXInject = "javax.inject:javax.inject:${DependenciesVersions.javaxInject}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${DependenciesVersions.okHttp}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${DependenciesVersions.rxjava}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${DependenciesVersions.rxkotlin}"
    const val rxPaper = "com.github.pakoito:RxPaper2:${DependenciesVersions.rxPaper}"

    const val moshi = "com.squareup.moshi:moshi:${DependenciesVersions.moshi}"
    const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${DependenciesVersions.moshi}"
}

object SupportLibs {
    const val appCompat = "androidx.appcompat:appcompat:${DependenciesVersions.appcompat}"
    const val design = "com.google.android.material:material:${DependenciesVersions.design}"
    const val cardview = "androidx.cardview:cardview:${DependenciesVersions.cardview}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${DependenciesVersions.constraintLayout}"
}

object TestLibs {
    const val junit = "junit:junit:${DependenciesVersions.junit}"
    const val assertjCore = "org.assertj:assertj-core:${DependenciesVersions.assertjCore}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${DependenciesVersions.mockitoKotlin}"
    const val mockitoInline = "org.mockito:mockito-inline:${DependenciesVersions.mockitoInline}"
    const val lifecycleTesting = "androidx.arch.core:core-testing:${DependenciesVersions.archCoreTesting}"
}

object MainAppDependencies {
    val dependencies = listOf(
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Modules.network, true),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Modules.disposable, true),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Modules.caching, true),
        DependecyConfiguration(ConfigurationType.API, Modules.feature_character_list, true),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, SupportLibs.appCompat),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.dagger),
        DependecyConfiguration(ConfigurationType.KAPT, Libs.daggerCompiler),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.daggerAndroid),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.daggerAndroidSupport),
        DependecyConfiguration(ConfigurationType.KAPT, Libs.daggerAndroidProcessor),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.javaXInject),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.kotlin),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.ktx)
    )

    val testDependencies = listOf(
        DependecyConfiguration(ConfigurationType.TEST_IMPLEMENTATION, TestLibs.junit)
    )
}

object PresentationDependencies {
    val dependencies = listOf(
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.lifecycleExtensions),
        DependecyConfiguration(ConfigurationType.KAPT, Libs.lifecycleCompiler),
        DependecyConfiguration(ConfigurationType.API, Libs.glide),
        DependecyConfiguration(ConfigurationType.KAPT, Libs.glideCompiler)
    )

    val testDependencies = listOf(
        DependecyConfiguration(ConfigurationType.TEST_IMPLEMENTATION, TestLibs.junit),
        DependecyConfiguration(ConfigurationType.TEST_IMPLEMENTATION, TestLibs.lifecycleTesting)
    )
}

object NetworkDependencies {
    val dependencies = listOf(
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.dagger),
        DependecyConfiguration(ConfigurationType.KAPT, Libs.daggerCompiler),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.javaXInject),
        DependecyConfiguration(ConfigurationType.API, Libs.retrofit),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.rxjavaAdapter),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.moshiConverter),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.loggingInterceptor),
        DependecyConfiguration(ConfigurationType.API, Libs.okHttp)
    )

    val testDependencies = listOf(
        DependecyConfiguration(ConfigurationType.TEST_IMPLEMENTATION, TestLibs.junit),
        DependecyConfiguration(ConfigurationType.TEST_IMPLEMENTATION, TestLibs.lifecycleTesting)
    )
}

object DisposableDependencies {
    val dependencies = listOf(
        DependecyConfiguration(ConfigurationType.API, Libs.rxJava),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.dagger),
        DependecyConfiguration(ConfigurationType.KAPT, Libs.daggerCompiler),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.javaXInject)
    )
}

object CacheDependencies {
    val dependencies = listOf(
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.rxJava),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.rxPaper),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.dagger),
        DependecyConfiguration(ConfigurationType.KAPT, Libs.daggerCompiler),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.javaXInject)
    )
}

object CommonLibraryDependencies {
    val dependecies = listOf(
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.kotlin)
    )
}

object FeatureCharacterListDependecies {
    val dependencies = listOf(
        //Modules
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Modules.presentation, true),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Modules.network, true),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Modules.caching, true),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Modules.disposable, true),

        // Support Libs
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.lifecycleExtensions),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, SupportLibs.design),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, SupportLibs.cardview),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, SupportLibs.constraintLayout),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, SupportLibs.appCompat),
        //Dagger
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.dagger),
        DependecyConfiguration(ConfigurationType.KAPT, Libs.daggerCompiler),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.daggerAndroid),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.daggerAndroidSupport),
        DependecyConfiguration(ConfigurationType.KAPT, Libs.daggerAndroidProcessor),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.javaXInject),

        //RX
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.rxJava),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.rxKotlin),

        //Misc
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.moshiConverter),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.moshi),
        DependecyConfiguration(ConfigurationType.KAPT, Libs.moshiCodeGen)
    )

    val testDependencies = listOf(
        DependecyConfiguration(ConfigurationType.TEST_IMPLEMENTATION, TestLibs.junit),
        DependecyConfiguration(ConfigurationType.TEST_IMPLEMENTATION, TestLibs.mockitoKotlin),
        DependecyConfiguration(ConfigurationType.TEST_IMPLEMENTATION, TestLibs.mockitoInline),
        DependecyConfiguration(ConfigurationType.TEST_IMPLEMENTATION, TestLibs.lifecycleTesting),
        DependecyConfiguration(ConfigurationType.TEST_IMPLEMENTATION, TestLibs.assertjCore)
    )
}