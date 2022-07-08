package br.com.zup.marvel.domain.usecase

import android.content.Context
import br.com.zup.marvel.constantes.MESSAGE_ERROR_LOAD_HEROI
import br.com.zup.marvel.data.local.database.HeroiDatabase
import br.com.zup.marvel.domain.model.Heroi
import br.com.zup.marvel.domain.repository.HeroiRepository
import br.com.zup.marvel.ui.viewstate.ViewState

//TODO: Verificar se o context é suficiente
class HeroiUseCase(context: Context) {
    private val heroiDAO = HeroiDatabase.getDatabase(context).heroiDao()
    private val heroiRepository = HeroiRepository(heroiDAO)

    suspend fun insertHeroi(heroi: Heroi): ViewState<Heroi> {
        return try {
            heroiRepository.insertHeroi(heroi)
            ViewState.Success(heroi)
        }catch (e: Exception){
            ViewState.Error(Exception(MESSAGE_ERROR_LOAD_HEROI))
        }
    }
}