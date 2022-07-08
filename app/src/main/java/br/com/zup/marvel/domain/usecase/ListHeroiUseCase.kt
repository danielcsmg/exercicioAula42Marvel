package br.com.zup.marvel.domain.usecase

import android.content.Context
import br.com.zup.marvel.constantes.MESSAGE_ERROR_LOAD_HEROI
import br.com.zup.marvel.data.local.database.HeroiDatabase
import br.com.zup.marvel.domain.model.Heroi
import br.com.zup.marvel.domain.repository.HeroiRepository
import br.com.zup.marvel.ui.viewstate.ViewState

class ListHeroiUseCase(context: Context) {
    private val heroiDAO = HeroiDatabase.getDatabase(context).heroiDao()
    private val heroiRepository = HeroiRepository(heroiDAO)

    suspend fun getAllHerois(): ViewState<List<Heroi>> {
        return try {
            val herois = heroiRepository.getAllHerois()
            ViewState.Success(herois)
        }catch (e: Exception){
            ViewState.Error(Exception(MESSAGE_ERROR_LOAD_HEROI))
        }
    }
}