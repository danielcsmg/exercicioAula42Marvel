package br.com.zup.marvel.ui.herois.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.marvel.databinding.CardHeroiBinding
import br.com.zup.marvel.domain.model.Heroi
import com.squareup.picasso.Picasso

class HeroisAdapter(
    private var listaHerois: List<Heroi>,
    val clickHeroi: (heroi: Heroi) -> Unit
) :
    RecyclerView.Adapter<HeroisAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardHeroiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listaHerois[position]
        holder.setarInformacoes(item)
        holder.binding.cvHeroi.setOnClickListener{
            clickHeroi(item)
        }
    }

    override fun getItemCount(): Int = listaHerois.size

    fun getListHerois(dbListaHeroi: List<Heroi>){
        listaHerois = dbListaHeroi
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: CardHeroiBinding): RecyclerView.ViewHolder(binding.root){
        fun setarInformacoes(heroi: Heroi){
            Picasso.get().load(heroi.imagem).into(binding.ivHeroi)
            binding.tvHeroi.text = heroi.nome
        }
    }
}