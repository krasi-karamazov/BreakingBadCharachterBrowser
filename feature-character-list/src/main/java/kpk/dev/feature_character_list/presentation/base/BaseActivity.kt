package kpk.dev.feature_character_list.presentation.base

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import kpk.dev.feature_character_list.R
import kpk.dev.feature_character_list.presentation.viewmodelfactory.ViewModelFactory
import kpk.dev.presentation.gone
import kpk.dev.presentation.visible

abstract class BaseActivity: AppCompatActivity() {

    protected var progressBar: ProgressBar? = null
    private var toolbar: Toolbar? = null
    private var snackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun init()
    protected fun setToolBar(toolbar: Toolbar) {
        this.toolbar = toolbar
        setSupportActionBar(this.toolbar)
    }

    protected fun setTitle(title: String) {
        this.toolbar?.title = title
    }

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

    protected fun displayError(
        msg: String?,
        actionTitle: String? = null,
        listener: (View) -> Unit
    ) {
        snackBar = Snackbar.make(
            findViewById(R.id.main_container),
            msg ?: getString(R.string.cannot_download_data),
            Snackbar.LENGTH_INDEFINITE
        ).apply {
            if (actionTitle != null) {
                setAction(actionTitle, listener)
            }
            show()
        }
    }

    protected fun hideError() {
        snackBar?.let {
            if (it.isShown) {
                it.dismiss()
            }
        }
    }

    inline fun <reified T: ViewModel> ViewModelFactory.get(): T = ViewModelProviders.of(this@BaseActivity, this)[T::class.java]
}