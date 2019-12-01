package kpk.dev.feature_character_list.presentation.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kpk.dev.feature_character_list.domain.usecase.GetSingleCharacterUseCase
import kpk.dev.feature_character_list.presentation.base.BaseViewModel
import kpk.dev.feature_character_list.presentation.model.CharacterItem
import kpk.dev.feature_character_list.presentation.model.mapToPresentation
import kpk.dev.presentation.Resource
import kpk.dev.presentation.setFailure
import kpk.dev.presentation.setLoading
import kpk.dev.presentation.setSuccess
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(
    val charactersListUseCase: GetSingleCharacterUseCase,
    disposable: CompositeDisposable
) : BaseViewModel(disposable) {

    private val characterData = MutableLiveData<Resource<CharacterItem>>()

    fun getCharacter(id: Int, refresh: Boolean): LiveData<Resource<CharacterItem>> {
        compositeDisposable.add(charactersListUseCase.getCharacter(id, refresh)
            .doOnSubscribe { characterData.setLoading() }
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe(
                { characterData.setSuccess(it) },
                { characterData.setFailure(it.message) })
        )
        return characterData
    }
}