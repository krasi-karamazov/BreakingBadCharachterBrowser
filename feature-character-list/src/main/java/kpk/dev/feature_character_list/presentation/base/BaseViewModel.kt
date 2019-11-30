package kpk.dev.feature_character_list.presentation.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel constructor(open var compositeDisposable: CompositeDisposable): ViewModel() {
    
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}