package kpk.dev.feature_character_list.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import kpk.dev.feature_character_list.presentation.viewmodelfactory.ViewModelFactory

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun init()

    inline fun <reified T: ViewModel> ViewModelFactory.get(): T = ViewModelProviders.of(this@BaseActivity, this)[T::class.java]
}