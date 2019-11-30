package kpk.dev.feature_character_list.presentation.characterlist

import kpk.dev.feature_character_list.R
import kpk.dev.feature_character_list.presentation.base.BaseActivity
import kpk.dev.feature_character_list.presentation.viewmodelfactory.ViewModelFactory
import javax.inject.Inject

class CharacterListActivity: BaseActivity() {

    @Inject
    internal lateinit var vmFactory: ViewModelFactory

    private lateinit var viewModel: CharacterListViewModel

    override fun getLayoutId(): Int = R.layout.activity_character_list

    override fun init() {
        viewModel = vmFactory.get()
        viewModel.getCharacterList(true)
    }

}