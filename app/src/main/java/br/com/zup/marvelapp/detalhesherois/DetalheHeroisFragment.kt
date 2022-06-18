package br.com.zup.marvelapp.detalhesherois

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.zup.marvelapp.MainActivity
import br.com.zup.marvelapp.databinding.FragmentDetalheHeroisBinding
import br.com.zup.marvelapp.model.Heroi

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

        val args = DetalheHeroisFragmentArgs.fromBundle(requireArguments())
        mostrarInformacoesHeroi(args.heroi)
    }

    private fun mostrarInformacoesHeroi(heroi: Heroi) {
        binding.ivHeroi.setImageResource(heroi.getImagem())
        binding.tvNomeHeroi.text = heroi.getNome()
        binding.tvDescricaoHeroi.text = heroi.getDescricao()
    }

    private fun alterarAppBar(){
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.title = "Detalhes"
    }
}