package kpk.dev.breakingbadcharachterbrowser.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kpk.dev.breakingbadcharachterbrowser.di.DaggerApplicationComponent
import kpk.dev.caching.PaperCache
import javax.inject.Inject

class BreakingBadApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        PaperCache.RxPaperInitializer.init(this)
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }
}