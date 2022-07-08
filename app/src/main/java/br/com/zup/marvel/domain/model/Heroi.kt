package br.com.zup.marvel.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Heroi(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val imagem: String,
    val nome: String,
    val descricao: String
): Parcelable