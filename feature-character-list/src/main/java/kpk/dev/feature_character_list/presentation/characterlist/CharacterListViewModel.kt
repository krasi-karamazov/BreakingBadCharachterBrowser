package kpk.dev.feature_character_list.presentation.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kpk.dev.feature_character_list.domain.usecase.GetCharactersListUseCase
import kpk.dev.feature_character_list.presentation.base.BaseViewModel
import kpk.dev.feature_character_list.presentation.model.CharacterItem
import kpk.dev.feature_character_list.presentation.model.mapToPresentation
import kpk.dev.presentation.Resource
import kpk.dev.presentation.setFailure
import kpk.dev.presentation.setLoading
import kpk.dev.presentation.setSuccess
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(val charactersListUseCase: GetCharactersListUseCase, disposable: CompositeDisposable): BaseViewModel(disposable) {
    private val charactersData = MutableLiveData<Resource<List<CharacterItem>>>()

    fun getCharacterList(refresh: Boolean): LiveData<Resource<List<CharacterItem>>> {
        compositeDisposable.add(charactersListUseCase.getCharacterList(refresh)
            .doOnSubscribe{ charactersData.setLoading() }
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe({charactersData.setSuccess(it)}, {charactersData.setFailure(it.message)}))
        return charactersData
    }
}