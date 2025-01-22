package com.example.trabalho1_cadastro

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalho1_cadastro.database.DatabaseHandler
import com.example.trabalho1_cadastro.databinding.ActivityMainBinding
import com.example.trabalho1_cadastro.databinding.ActivityPessoaBinding
import com.example.trabalho1_cadastro.entity.Pessoa

class PessoaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPessoaBinding
    private lateinit var banco : DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPessoaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            btCadastrarPessoa()
        }

        banco = DatabaseHandler(this)


    }

    private fun btCadastrarPessoa() {
        val pessoa = Pessoa(0, binding.etNome.text.toString())

        if (pessoa.nome.isNotEmpty() and !banco.verificarPessoaExistente(pessoa.nome)) {
            banco.insertPessoa(pessoa)

            Toast.makeText(this, "Pessoa inserida com sucesso", Toast.LENGTH_LONG).show()

            binding.etNome.text.clear()
        } else {
            Toast.makeText(this, "Por favor, insira um nome valido e ainda não cadastrado", Toast.LENGTH_LONG).show()
        }
    }

}