package br.com.zup.marvel.ui.herois.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.marvel.constantes.MESSAGE_ERROR_INSERT_HEROI
import br.com.zup.marvel.constantes.MESSAGE_ERROR_LOAD_HEROI
import br.com.zup.marvel.domain.model.Heroi
import br.com.zup.marvel.domain.usecase.HeroiUseCase
import br.com.zup.marvel.domain.usecase.ListHeroiUseCase
import br.com.zup.marvel.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaHeroiViewModel(application: Application): AndroidViewModel(application) {
    private val heroiUseCase = ListHeroiUseCase(application)
    val listHeroiState = MutableLiveData<ViewState<List<Heroi>>>()

    fun getAllHerois() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    heroiUseCase.getAllHerois()
                }
                listHeroiState.value = response
            } catch (e: Exception) {
                listHeroiState.value = ViewState.Error(Throwable(MESSAGE_ERROR_LOAD_HEROI))
            }
        }
    }
}