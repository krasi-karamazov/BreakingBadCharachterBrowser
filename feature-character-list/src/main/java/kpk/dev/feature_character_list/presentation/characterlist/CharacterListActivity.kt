package kpk.dev.feature_character_list.presentation.characterlist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

    private val filterFab: FloatingActionButton by lazy {
        findViewById<FloatingActionButton>(R.id.fab)
    }

    private lateinit var seasonsArray: IntArray

    private val searchObserver = Observer<Resource<List<CharacterItem>>> {
        when (it.resourceState) {
            ResourceState.FAILURE -> displayError(getString(R.string.search_error), null) {}
            ResourceState.SUCCESS -> {
                hideError()
                charactersAdapter.updateData(it.data)
            }
        }
    }

    private val filterObserver = Observer<Resource<List<CharacterItem>>> {
        when (it.resourceState) {
            ResourceState.FAILURE -> {
                displayError(getString(R.string.filter_error), null) {}
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

        seasonsArray = resources.getIntArray(R.array.seasonsInts)

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

        filterFab.setOnClickListener {
            val args = Bundle()
            if (viewModel.appliedFilter.isNotEmpty()) {
                args.putBooleanArray(FilterDialogFragment.EXTRA_SELECTIONS, viewModel.appliedFilter)
            }
            FilterDialogFragment.getInstance(args) {
                val appearanceList = mutableListOf<Int>()
                if (it.size() > 0) {
                    for (i in 0 until it.size()) {
                        viewModel.appliedFilter[it.keyAt(i)] = it.valueAt(i)
                        if (it.valueAt(i)) {
                            appearanceList.add(seasonsArray[it.keyAt(i)])
                        }
                    }
                } else {
                    viewModel.appliedFilter.fill(false)
                }
                viewModel.filterCharactersBySeasonAppearance(appearanceList)
                    .observe(this@CharacterListActivity, filterObserver)
            }
                .show(supportFragmentManager, FilterDialogFragment.TAG)
        }
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
                executeSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                executeSearch(newText)
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun executeSearch(query: String?) {
        query?.let {
            if (it.length >= 2) {
                viewModel.searchForCharacterByName(it)
                    .observe(this@CharacterListActivity, searchObserver)
            } else {
                if (it.isEmpty() || it.length == 1) {
                    downloadData()
                }
            }
        }
    }
}