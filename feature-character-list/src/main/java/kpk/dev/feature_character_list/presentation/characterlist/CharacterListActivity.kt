package kpk.dev.feature_character_list.presentation.characterlist

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kpk.dev.feature_character_list.R
import kpk.dev.feature_character_list.presentation.base.BaseActivity
import kpk.dev.feature_character_list.presentation.viewmodelfactory.ViewModelFactory
import kpk.dev.presentation.ResourceState
import javax.inject.Inject

class CharacterListActivity: BaseActivity() {

    @Inject
    internal lateinit var vmFactory: ViewModelFactory

    private lateinit var viewModel: CharacterListViewModel

    private lateinit var snackBar:Snackbar

    private val charactersRecyclerView: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_characters)
    }
    private val charactersAdapter: CharactersAdapter by lazy {
        CharactersAdapter {
            //start activity
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_character_list

    override fun init() {
        viewModel = vmFactory.get()
        progressBar = findViewById(R.id.pb_char_list)

        charactersRecyclerView.apply {
            val rvLayoutManager =
                GridLayoutManager(this@CharacterListActivity, 2, GridLayoutManager.VERTICAL, false)
            layoutManager = rvLayoutManager
            adapter = charactersAdapter
        }
        downloadData()
    }

    private fun downloadData() {
        viewModel.getCharacterList(true).observe(this, Observer {
            when (it.resourceState) {
                ResourceState.LOADING -> showProgress()
                ResourceState.FAILURE -> {
                    hideProgress()
                    displayError(it.message)
                }
                ResourceState.SUCCESS -> {
                    hideProgress()
                    charactersAdapter.updateData(it.data)
                }
            }
        })
    }

    private fun displayError(msg: String?) {
        snackBar =  Snackbar.make(findViewById(R.id.main_container), msg ?: getString(R.string.cannot_download_data), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.try_again)) {downloadData()}

        snackBar.show()
    }

}