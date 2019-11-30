package kpk.dev.breakingbadcharachterbrowser.application

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import kpk.dev.breakingbadcharachterbrowser.di.DaggerApplicationComponent
import kpk.dev.caching.PaperCache
import javax.inject.Inject

class BreakingBadApplication: Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        PaperCache.RxPaperInitializer.init(this)
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }
}