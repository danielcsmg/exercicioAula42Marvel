package br.com.zup.marvel.ui.cadastroheroi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.zup.marvel.R
import br.com.zup.marvel.constantes.MESSAGE_SUCCESS_INSERT_HEROI
import br.com.zup.marvel.databinding.FragmentCadastroHeroiBinding
import br.com.zup.marvel.domain.model.Heroi
import br.com.zup.marvel.ui.cadastroheroi.viewmodel.CadastroHeroiViewModel
import br.com.zup.marvel.ui.viewstate.ViewState

class CadastroHeroiFragment : Fragment() {
    private lateinit var binding: FragmentCadastroHeroiBinding
    private val viewModel: CadastroHeroiViewModel by lazy {
        ViewModelProvider(this)[CadastroHeroiViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCadastroHeroiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        observer()
        binding.btnCadastro.setOnClickListener {
            val heroi = verifyFields()
            heroi?.let {
                viewModel.insertHeroi(it)
                clearFields()
            }
        }

        binding.btnListaHeroi.setOnClickListener {
            findNavController().navigate(R.id.action_cadastroHeroiFragment_to_listaHeroisFragment)
        }
    }

    private fun verifyFields(): Heroi? {
        val imagem = binding.etImageUri.text.toString()
        val nome = binding.etNomeHeroi.text.toString()
        val descricao = binding.etDescricaoHeroi.text.toString()

        return if (imagem.isNotBlank() && nome.isNotBlank() && descricao.isNotBlank()) {
            Heroi(
                imagem = imagem,
                nome = nome,
                descricao = descricao
            )
        }else{
            messageError(imagem, nome, descricao)
            null
        }
    }

    fun messageError(image: String, nome: String, descricao: String){
        binding.etImageUri.error = if (image.isBlank()) "Insira a URL da Imagem" else null
        binding.etNomeHeroi.error = if (nome.isBlank()) "Insira o nome do heroi" else null
        binding.etDescricaoHeroi.error = if (descricao.isBlank()) "Insira a descrição do heroi" else null
    }

    fun clearFields(){
        binding.etImageUri.text.clear()
        binding.etNomeHeroi.text.clear()
        binding.etDescricaoHeroi.text.clear()
    }

    private fun observer(){
        viewModel.heroiAddState.observe(this){
            when(it){
                is ViewState.Success -> {
                    Toast.makeText(context, MESSAGE_SUCCESS_INSERT_HEROI, Toast.LENGTH_SHORT).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(context, it.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}