package br.com.zup.marvel.ui.detalhesherois

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.zup.marvel.constantes.HEROI
import br.com.zup.marvel.databinding.FragmentDetalheHeroisBinding
import br.com.zup.marvel.domain.model.Heroi
import br.com.zup.marvel.ui.home.view.MainActivity
import com.squareup.picasso.Picasso

class DetalheHeroisFragment : Fragment() {
    private lateinit var binding: FragmentDetalheHeroisBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetalheHeroisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alterarAppBar()

    }

    override fun onResume() {
        super.onResume()

        val heroi = getBundleHeroi()
        mostrarInformacoesHeroi(heroi)
    }

    fun getBundleHeroi(): Heroi? {
        return arguments?.getParcelable<Heroi>(HEROI)
    }

    private fun mostrarInformacoesHeroi(heroi: Heroi?) {
        heroi?.let {
            Picasso.get().load(it.imagem).into(binding.ivHeroi)
            binding.tvNomeHeroi.text = it.nome
            binding.tvDescricaoHeroi.text = it.descricao
        }
    }

    private fun alterarAppBar(){
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.title = "Detalhes"
    }
}