package kpk.dev.feature_character_list.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kpk.dev.feature_character_list.presentation.characterdetails.CharacterDetailsViewModel
import kpk.dev.feature_character_list.presentation.characterlist.CharacterListViewModel
import kpk.dev.feature_character_list.presentation.viewmodelfactory.ViewModelFactory
import kpk.dev.presentation.di.ViewModelKey

@Module
abstract class FeatureCharacterListViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CharacterListViewModel::class)
    internal abstract fun bindCharacterListViewModel(viewModel: CharacterListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailsViewModel::class)
    internal abstract fun bindCharacterDetailsViewModel(viewModel: CharacterDetailsViewModel): ViewModel
}