package kpk.dev.disposable

import dagger.Module
import io.reactivex.disposables.CompositeDisposable

@Module
class DisposableModule {

    fun provideCompositeDisposable() = CompositeDisposable()
}