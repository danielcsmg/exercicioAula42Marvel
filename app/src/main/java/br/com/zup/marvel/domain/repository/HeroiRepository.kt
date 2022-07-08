package br.com.zup.marvel.domain.repository

import br.com.zup.marvel.data.local.database.dao.HeroiDAO
import br.com.zup.marvel.domain.model.Heroi

class HeroiRepository(private val heroiDAO: HeroiDAO) {
    suspend fun getAllHerois(): List<Heroi> = heroiDAO.getAllHerois()

    suspend fun insertHeroi(heroi: Heroi){
        heroiDAO.insertHeroi(heroi)
    }
}