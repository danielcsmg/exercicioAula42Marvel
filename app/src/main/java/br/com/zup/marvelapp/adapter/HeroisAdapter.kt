package br.com.zup.marvelapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.marvelapp.databinding.CardHeroiBinding
import br.com.zup.marvelapp.databinding.FragmentCadastroHeroisBinding
import br.com.zup.marvelapp.model.Heroi

class HeroisAdapter(
    private var listaHerois: MutableList<Heroi>,
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

    class ViewHolder(val binding: CardHeroiBinding): RecyclerView.ViewHolder(binding.root){
        fun setarInformacoes(heroi: Heroi){
            binding.ivHeroi.setImageResource(heroi.getImagem())
            binding.tvHeroi.text = heroi.getNome()
        }
    }
}