package kpk.dev.feature_character_list.data.di

import dagger.Module
import dagger.Provides
import kpk.dev.feature_character_list.data.datasource.ICharactersOfflineDataSource
import kpk.dev.feature_character_list.data.datasource.ICharactersRemoteDataSource
import kpk.dev.feature_character_list.data.repository.CharacterRepository
import kpk.dev.feature_character_list.domain.repository.ICharacterRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(
        characterOfflineDataSource: ICharactersOfflineDataSource,
        charactersRemoteDataSource: ICharactersRemoteDataSource
    ): ICharacterRepository =
        CharacterRepository(charactersRemoteDataSource, characterOfflineDataSource)
}