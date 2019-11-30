package kpk.dev.feature_character_list.datasource.di

import dagger.Module
import dagger.Provides
import kpk.dev.feature_character_list.datasource.remote.BreakingBadCharacterApi
import retrofit2.Retrofit

@Module
class BreakingBadApiModule {
    @Provides
    fun provideBreakingBadApi(retrofit: Retrofit): BreakingBadCharacterApi = retrofit.create(BreakingBadCharacterApi::class.java)
}