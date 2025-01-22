package com.example.trabalho1_cadastro

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalho1_cadastro.database.DatabaseHandler
import com.example.trabalho1_cadastro.databinding.ActivityItemBinding
import com.example.trabalho1_cadastro.entity.Item

class ItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemBinding
    private lateinit var banco : DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        banco = DatabaseHandler(this)

        binding.btCadastrarItem.setOnClickListener {
            btCadastrarItem()
        }

        carregarPessoasNoSpinner()

    }

    private fun btCadastrarItem() {
        val item = Item(0,
            binding.etDescricao.text.toString(),
            binding.etValor.text.toString().toDouble(),
            binding.spinner.selectedItem.toString())

        val cursor = banco.listPagadores()
        val cursor_pessoas = banco.listPessoas()
        var nomeJaCadastrado = false

        if(binding.spinner.selectedItem.toString() == "Todos"){

            val num_pessoas = banco.contarPessoas()
            val media = binding.etValor.text.toString().toDouble() / num_pessoas


            if (cursor_pessoas.moveToFirst()) {
                do {
                    val nome = cursor_pessoas.getString(cursor_pessoas.getColumnIndexOrThrow("nome"))

                    if (banco.verificarPagadorExistente(nome)) {
                        banco.atualizarValor(banco.findPagador(nome)!!, media)
                    } else {
                        val pagador = Item(0, binding.etDescricao.text.toString(), media, nome)
                        banco.insertItem(pagador)
                    }
                } while (cursor_pessoas.moveToNext())
            }
            binding.etDescricao.text.clear()
            binding.etValor.text.clear()
            Toast.makeText( this, "Item dividido para todos com sucesso", Toast.LENGTH_LONG ).show()
        } else {
            if (cursor.moveToFirst()) {
                do {
                    val nome = cursor.getString(cursor.getColumnIndexOrThrow("pagadores"))
                    if (binding.spinner.selectedItem.toString() == nome) {
                        nomeJaCadastrado = true
                        break
                    }
                } while (cursor.moveToNext())
            }

            if (!nomeJaCadastrado) {
                banco.insertItem(item)
                binding.etDescricao.text.clear()
                binding.etValor.text.clear()
                Toast.makeText( this, "Item inserido com sucesso para ${item.pagadores} pagar", Toast.LENGTH_LONG ).show()
            }else{
                val nome = cursor.getString(cursor.getColumnIndexOrThrow("pagadores"))
                banco.atualizarValor(banco.findPagador(nome)!!, item.valor)
                binding.etDescricao.text.clear()
                binding.etValor.text.clear()
                Toast.makeText( this, "Item adicionado a ${nome}", Toast.LENGTH_LONG ).show()
            }
        }
    }


    private fun carregarPessoasNoSpinner() {
        val cursor = banco.listPessoas()
        val nome = mutableListOf<String>()
        while (cursor.moveToNext()) {
            nome.add(cursor.getString(1))
        }
        nome.add("Todos")

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, nome)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
    }
}