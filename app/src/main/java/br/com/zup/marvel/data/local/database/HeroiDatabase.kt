package br.com.zup.marvel.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.zup.marvel.data.local.database.dao.HeroiDAO
import br.com.zup.marvel.domain.model.Heroi

@Database(entities = [Heroi::class], version = 1)
abstract class HeroiDatabase: RoomDatabase() {
    abstract fun heroiDao(): HeroiDAO

    companion object {
        @Volatile
        private var INSTANCE: HeroiDatabase? = null

        fun getDatabase(context: Context): HeroiDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HeroiDatabase::class.java,
                    "heroi_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}