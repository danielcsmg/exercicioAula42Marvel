package br.com.zup.marvel.ui.cadastroheroi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.marvel.constantes.MESSAGE_ERROR_INSERT_HEROI
import br.com.zup.marvel.domain.model.Heroi
import br.com.zup.marvel.domain.usecase.HeroiUseCase
import br.com.zup.marvel.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CadastroHeroiViewModel(application: Application): AndroidViewModel(application) {
    private val heroiUseCase = HeroiUseCase(application)
    val heroiAddState = MutableLiveData<ViewState<Heroi>>()

    fun insertHeroi(heroi: Heroi) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    heroiUseCase.insertHeroi(heroi)
                }
                heroiAddState.value = response
            } catch (e: Exception) {
                heroiAddState.value = ViewState.Error(Throwable(MESSAGE_ERROR_INSERT_HEROI))
            }
        }
    }
}