package br.com.zup.marvel.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.zup.marvel.domain.model.Heroi


@Dao
interface HeroiDAO {
    @Query("SELECT * FROM heroi ORDER BY nome ASC")
    fun getAllHerois(): List<Heroi>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertHeroi(heroi: Heroi)
}