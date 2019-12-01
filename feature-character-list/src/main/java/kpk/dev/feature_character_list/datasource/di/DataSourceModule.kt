package kpk.dev.feature_character_list.datasource.di

import dagger.Module
import dagger.Provides
import kpk.dev.caching.PaperCache
import kpk.dev.feature_character_list.data.datasource.ICharactersOfflineDataSource
import kpk.dev.feature_character_list.data.datasource.ICharactersRemoteDataSource
import kpk.dev.feature_character_list.datasource.cache.CharactersOfflineDataSource
import kpk.dev.feature_character_list.datasource.remote.BreakingBadCharacterApi
import kpk.dev.feature_character_list.datasource.remote.CharactersRemoteDataSource
import kpk.dev.feature_character_list.domain.model.Character
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideOfflineDataSource(paperCache: PaperCache<List<Character>>): ICharactersOfflineDataSource =
        CharactersOfflineDataSource(paperCache)

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: BreakingBadCharacterApi): ICharactersRemoteDataSource =
        CharactersRemoteDataSource(api)

    @Provides
    @Singleton
    fun provideCharacterListPaperCache(): PaperCache<List<Character>> = PaperCache()
}