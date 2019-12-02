package kpk.dev.feature_character_list.presentation.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kpk.dev.feature_character_list.domain.usecase.GetCharactersListUseCase
import kpk.dev.feature_character_list.domain.usecase.SearchForCharacterByNameUseCase
import kpk.dev.feature_character_list.presentation.base.BaseViewModel
import kpk.dev.feature_character_list.presentation.model.CharacterItem
import kpk.dev.feature_character_list.presentation.model.mapToPresentation
import kpk.dev.presentation.Resource
import kpk.dev.presentation.setFailure
import kpk.dev.presentation.setLoading
import kpk.dev.presentation.setSuccess
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    private val charactersListUseCase: GetCharactersListUseCase,
    private val searchForCharacterByName: SearchForCharacterByNameUseCase,
    disposable: CompositeDisposable
) : BaseViewModel(disposable) {
    private val charactersData = MutableLiveData<Resource<List<CharacterItem>>>()

    private val searchData = MutableLiveData<Resource<List<CharacterItem>>>()

    fun getCharacterList(refresh: Boolean): LiveData<Resource<List<CharacterItem>>> {
        compositeDisposable.add(charactersListUseCase.getCharacterList(refresh)
            .doOnSubscribe{ charactersData.setLoading() }
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe({ charactersData.setSuccess(it) }, { charactersData.setFailure(it.message) })
        )
        return charactersData
    }

    fun searchForCharacterByName(name: String): LiveData<Resource<List<CharacterItem>>> {
        compositeDisposable.add(searchForCharacterByName.searchForCharacterByName(name)
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe({ searchData.setSuccess(it) }, { searchData.setFailure(it.message) })
        )
        return searchData
    }
}