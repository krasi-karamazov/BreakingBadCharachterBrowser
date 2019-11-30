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
    val versionCode = 1
    val versionName = "1.0"
}

object DependenciesVersions {
    const val gradle = "3.5.2"
    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29

    const val appcompat = "1.0.2"
    const val design = "1.0.0"
    const val cardview = "1.0.0"
    const val recyclerview = "1.0.0"

    const val ktx = "1.0.0-alpha1"

    const val kotlin = "1.3.41"
    const val timber = "4.7.1"
    const val rxjava = "2.2.15"
    const val rxkotlin = "2.4.0"
    const val rxPaper = "1.4.0"
    const val retrofit = "2.6.0"
    const val loggingInterceptor = "4.0.0"
    const val glide = "4.9.0"
    const val okHttp = "4.2.1"

    const val dagger = "2.23.1"
    const val javaxInject = "1"

    const val moshi = "1.8.0"
    const val lifecycle = "2.0.0"
    const val leakCanary = "2.0-alpha-2"

    const val junit = "4.12"
    const val assertjCore = "3.12.2"
    const val mockitoKotlin = "2.1.0"
    const val mockitoInline = "3.0.0"
}

object Libs {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${DependenciesVersions.kotlin}"
    val ktx = "androidx.core:core-ktx:${DependenciesVersions.ktx}"
    val timber = "com.jakewharton.timber:timber:${DependenciesVersions.timber}"
    val retrofit = "com.squareup.retrofit2:retrofit:${DependenciesVersions.retrofit}"
    val rxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${DependenciesVersions.retrofit}"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:${DependenciesVersions.retrofit}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${DependenciesVersions.loggingInterceptor}"
    val glide = "com.github.bumptech.glide:glide:${DependenciesVersions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${DependenciesVersions.glide}"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${DependenciesVersions.lifecycle}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${DependenciesVersions.lifecycle}"
    val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:${DependenciesVersions.leakCanary}"
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
}

object SupportLibs {
    val appCompat = "androidx.appcompat:appcompat:${DependenciesVersions.appcompat}"
    val design = "com.google.android.material:material:${DependenciesVersions.design}"
    val cardview = "androidx.cardview:cardview:${DependenciesVersions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${DependenciesVersions.recyclerview}"
}

object TestLibs {
    val junit = "junit:junit:${DependenciesVersions.junit}"
    val assertjCore = "org.assertj:assertj-core:${DependenciesVersions.assertjCore}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${DependenciesVersions.mockitoKotlin}"
    val lifecycleTesting = "androidx.arch.core:core-testing:${DependenciesVersions.lifecycle}"
}

object MainAppDependencies {
    val dependencies = listOf(
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Modules.network, true),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Modules.disposable, true),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Modules.caching, true),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Modules.feature_character_list, true),
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
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.rxJava),
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

        // Support Libs
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.lifecycleExtensions),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, SupportLibs.design),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, SupportLibs.cardview),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, SupportLibs.appCompat),
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, SupportLibs.recyclerview),
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
        DependecyConfiguration(ConfigurationType.IMPLEMENTATION, Libs.moshiConverter)
    )

    val testDependencies = listOf(
        DependecyConfiguration(ConfigurationType.TEST_IMPLEMENTATION, TestLibs.junit),
        DependecyConfiguration(ConfigurationType.TEST_IMPLEMENTATION, TestLibs.mockitoKotlin),
        DependecyConfiguration(ConfigurationType.TEST_IMPLEMENTATION, TestLibs.lifecycleTesting),
        DependecyConfiguration(ConfigurationType.TEST_IMPLEMENTATION, TestLibs.assertjCore)
    )
}