package kpk.dev.feature_character_list.presentation.base

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import kpk.dev.feature_character_list.presentation.viewmodelfactory.ViewModelFactory
import kpk.dev.presentation.gone
import kpk.dev.presentation.visible

abstract class BaseActivity: AppCompatActivity() {

    protected var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun init()

    protected fun showProgress() {
        progressBar?.let {
            it.visible()
        }
    }

    protected fun hideProgress() {
        progressBar?.let {
            it.gone()
        }
    }

    inline fun <reified T: ViewModel> ViewModelFactory.get(): T = ViewModelProviders.of(this@BaseActivity, this)[T::class.java]
}