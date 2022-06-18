package br.com.zup.marvelapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Heroi(
    private val imagem: Int,
    private val nome: String,
    private val descricao: String
): Parcelable {
    fun getImagem() = imagem
    fun getNome() = nome
    fun getDescricao() = descricao
}