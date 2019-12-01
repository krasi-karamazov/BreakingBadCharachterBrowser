package kpk.dev.feature_character_list.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kpk.dev.feature_character_list.presentation.characterdetails.CharacterDetailsActivity
import kpk.dev.feature_character_list.presentation.characterlist.CharacterListActivity

@Module
abstract class FeatureCharacterListActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindCharacterListActivity(): CharacterListActivity

    @ContributesAndroidInjector
    abstract fun bindCharacterDetailsActivity(): CharacterDetailsActivity
}