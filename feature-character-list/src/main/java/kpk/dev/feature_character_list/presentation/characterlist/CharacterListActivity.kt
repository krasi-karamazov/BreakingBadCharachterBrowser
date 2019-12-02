package kpk.dev.feature_character_list.presentation.characterlist

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kpk.dev.feature_character_list.R
import kpk.dev.feature_character_list.presentation.base.BaseActivity
import kpk.dev.feature_character_list.presentation.characterdetails.CharacterDetailsActivity
import kpk.dev.feature_character_list.presentation.model.CharacterItem
import kpk.dev.feature_character_list.presentation.viewmodelfactory.ViewModelFactory
import kpk.dev.presentation.Resource
import kpk.dev.presentation.ResourceState
import javax.inject.Inject


class CharacterListActivity: BaseActivity() {

    @Inject
    internal lateinit var vmFactory: ViewModelFactory

    private lateinit var viewModel: CharacterListViewModel

    private val charactersRecyclerView: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_characters)
    }

    private val searchObserver = Observer<Resource<List<CharacterItem>>> {
        when (it.resourceState) {
            ResourceState.FAILURE -> {
                displayError(it.message, getString(R.string.try_again)) { downloadData() }
            }
            ResourceState.SUCCESS -> {
                hideError()
                charactersAdapter.updateData(it.data)
            }
        }
    }

    private val charactersAdapter: CharactersAdapter by lazy {
        CharactersAdapter {
            val intent =
                Intent(this@CharacterListActivity, CharacterDetailsActivity::class.java).apply {
                    putExtra(CharacterDetailsActivity.EXTRA_CHARACTER_ID, it)
                }
            startActivity(intent)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_character_list

    override fun init() {
        setToolBar(findViewById(R.id.toolbar))
        setTitle(getString(R.string.app_title))

        viewModel = vmFactory.get()
        progressBar = findViewById(R.id.pb_char_list)

        charactersRecyclerView.apply {
            layoutManager = GridLayoutManager(
                this@CharacterListActivity,
                2,
                GridLayoutManager.VERTICAL,
                false
            )
            adapter = charactersAdapter
        }
        downloadData()
    }

    private fun downloadData() {
        viewModel.getCharacterList(false)
            .observe(this, Observer {
            when (it.resourceState) {
                ResourceState.LOADING -> {
                    showProgress()
                }
                ResourceState.FAILURE -> {
                    hideProgress()
                    displayError(it.message, getString(R.string.try_again)) { downloadData() }
                }
                ResourceState.SUCCESS -> {
                    hideError()
                    hideProgress()
                    charactersAdapter.updateData(it.data)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        val searchView: SearchView? = searchItem?.actionView as SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                searchItem.collapseActionView()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.length >= 2) {
                        viewModel.searchForCharacterByName(it)
                            .observe(this@CharacterListActivity, searchObserver)
                    } else {
                        if (it.isEmpty() || it.length == 1) {
                            downloadData()
                        }
                    }
                }
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
}