package kpk.dev.breakingbadcharachterbrowser.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    @Named("app_context")
    fun provideApplicationContext(application: Application): Context = application.applicationContext
}