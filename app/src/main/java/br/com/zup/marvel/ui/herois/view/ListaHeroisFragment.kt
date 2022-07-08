package br.com.zup.marvel.ui.herois.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.marvel.R
import br.com.zup.marvel.constantes.HEROI
import br.com.zup.marvel.databinding.FragmentListaHeroisBinding
import br.com.zup.marvel.domain.model.Heroi
import br.com.zup.marvel.ui.herois.view.adapter.HeroisAdapter
import br.com.zup.marvel.ui.herois.viewmodel.ListaHeroiViewModel
import br.com.zup.marvel.ui.home.view.MainActivity
import br.com.zup.marvel.ui.viewstate.ViewState

class ListaHeroisFragment : Fragment() {
    private lateinit var binding: FragmentListaHeroisBinding
    private val listaHerois = mutableListOf<Heroi>()
    private val viewModel: ListaHeroiViewModel by lazy {
        ViewModelProvider(this)[ListaHeroiViewModel::class.java]
    }
    val adapter: HeroisAdapter by lazy {
        HeroisAdapter(listOf(), this::mostrarDetalheHeroi)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListaHeroisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        alterarAppBar()
        viewModel.getAllHerois()
        observer()

        val recyclerView = binding.rvListaHerois
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun mostrarDetalheHeroi(heroi: Heroi) {
        val bundle = bundleOf(HEROI to heroi)
        findNavController().navigate(R.id.action_listaHeroisFragment_to_detalheHeroisFragment, bundle)
    }

    private fun observer(){
        viewModel.listHeroiState.observe(this.viewLifecycleOwner){
            when(it){
                is ViewState.Success -> {
                    adapter.getListHerois(it.data)
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun alterarAppBar() {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity).supportActionBar?.title = "Heróis"
    }
}