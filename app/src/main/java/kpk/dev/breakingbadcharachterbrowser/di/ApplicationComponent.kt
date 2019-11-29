package kpk.dev.breakingbadcharachterbrowser.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import kpk.dev.breakingbadcharachterbrowser.application.BreakingBadApplication
import kpk.dev.disposable.DisposableModule
import kpk.dev.network.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, NetworkModule::class, DisposableModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(application: BreakingBadApplication)
}