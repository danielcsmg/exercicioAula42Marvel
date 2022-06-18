package br.com.zup.marvelapp.herois

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.marvelapp.MainActivity
import br.com.zup.marvelapp.R
import br.com.zup.marvelapp.adapter.HeroisAdapter
import br.com.zup.marvelapp.constantes.*
import br.com.zup.marvelapp.databinding.FragmentCadastroHeroisBinding
import br.com.zup.marvelapp.model.Heroi

class ListaHeroisFragment : Fragment() {
    private lateinit var binding: FragmentCadastroHeroisBinding
    private val listaHerois = mutableListOf(
        Heroi(R.drawable.thor, "Thor", DESCRICAO_THOR),
        Heroi(R.drawable.captain_america, "Capitão América", DESCRICAO_CAP_AMERICA),
        Heroi(R.drawable.spiderman, "Homem-aranha", DESCRICAO_HOMEM_ARANHA),
        Heroi(R.drawable.dr_strange, "Dr. Estranho", DESCRICAO_DR_ESTRANHO),
        Heroi(R.drawable.homem_ferro, "Homem de Ferro", DESCRICAO_HOMEM_DE_FERRO),
        Heroi(R.drawable.hulk, "Hulk", DESCRICAO_HULK),
        Heroi(R.drawable.mulher_invisivel, "Mulher Invisível", DESCRICAO_MULHER_INVISIVEL),
        Heroi(R.drawable.star_lord, "Senhor das Estrelas", DESCRICAO_STAR_LORD),
        Heroi(R.drawable.vampira, "Vampira", DESCRICAO_VAMPIRA),
        Heroi(R.drawable.victor_heroi, "Super-Urso", "Sem descrição."),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCadastroHeroisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alterarAppBar()

        val recyclerView = binding.rvListaHerois
        recyclerView.adapter = HeroisAdapter(listaHerois, this::mostrarDetalheHeroi)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun mostrarDetalheHeroi(heroi: Heroi) {
        val action =
            ListaHeroisFragmentDirections.actionCadastroHeroisFragmentToDetalheHeroisFragment(heroi)
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun alterarAppBar() {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity).supportActionBar?.title = "Heróis"
    }
}